package tbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the TBSServer interface for SOFTENG 251 assignment 2.
 * {@inheritDoc}
 *
 * @author Timo van Veenendaal
 * @version 1.0.0
 */
public class TBSServerImpl implements TBSServer {
    private final IDGenerator idGenerator = new AutoIncrementIDGenerator();
    private final TBSServerState state = new MemoryTBSServerState();
    private final SalesReportFormatter salesReportFormatter = new TBSSalesReportFormatter(state);

    public TBSServerImpl() {
    }

    public String initialise(String path) {
        if(path == null) {
            return error("path cannot be null!");
        }

        TheatreParser parser;
        try {
            parser = new TheatreParser(new File(path));
        } catch(FileNotFoundException | TBSException e) {
            return error(e.getMessage());
        }

        List<Theatre> theatresToAdd;
        try {
            theatresToAdd = parser.readAllRemaining();
        } catch(TBSException e) {
        	return error(e.getMessage());
        }

        state.getTheatreRepository().addAll(theatresToAdd);
        return "";
    }

    public List<String> getTheatreIDs() {
        return state.getTheatreRepository().getAllIDs();
    }

    public List<String> getArtistIDs() {
        return state.getArtistRepository().getAllIDs();
    }

    public List<String> getArtistNames() {
        return state.getArtistRepository().getArtistNames();
    }

    public List<String> getActIDsForArtist(String artistID) {
        if(artistID == null) {
            return listError("artist ID cannot be null.");
        }
        if(artistID.isEmpty()) {
            return listError("artist ID cannot be empty.");
        }
        if(!idGenerator.isValidID(artistID)) {
            return listError("artist ID is not a valid ID.");
        }

        ArtistRepository artists = state.getArtistRepository();
        if(!artists.hasItemOfId(artistID)) {
            return listError("no artist with id " + artistID);
        }

        Artist artist = artists.getById(artistID);
        return state.getActRepository().getActIdsForArtist(artist);
    }

    public List<String> getPeformanceIDsForAct(String actID) {
        if(actID == null) {
            return listError("act ID cannot be null.");
        }
        if(!idGenerator.isValidID(actID)) {
            return listError("act ID is not a valid ID.");
        }

        ActRepository acts = state.getActRepository();
        if(!acts.hasItemOfId(actID)) {
            return listError("no act with ID " + actID);
        }

        Act act = acts.getById(actID);
        return state.getPerformanceRepository().getPerformancesIDsForAct(act);
    }

    public List<String> getTicketIDsForPerformance(String performanceID) {
        if(performanceID == null) {
            return listError("performanceID is null!");
        }

        if(performanceID.isEmpty()) {
            return listError("performanceID cannot be empty");
        }

        Performance performance = state.getPerformanceRepository().getById(performanceID);

        if(performance == null) {
            return listError("No performance with ID " + performanceID);
        }

        return performance.getIssuedTicketIds();
    }

    public String addArtist(String name) {
        if(name == null) {
            return error("name is null!");
        }

        if(name.isEmpty()) {
            return error("name cannot be empty!");
        }

        Artist artist = new Artist(idGenerator, name);
        try {
            state.getArtistRepository().add(artist);
        } catch(TBSException e) {
            return error(e.getMessage());
        }
        return artist.getId();
    }

    public String addAct(String title, String artistID, int minutesDuration) {
        if(title == null) {
            return error("title is null!");
        }

        if(artistID == null) {
            return error("artistID is null!");
        }

        if(artistID.isEmpty()) {
            return error("artistID is empty!");
        }

        Artist artist = state.getArtistRepository().getById(artistID);
        if(artist == null) {
            return error("No artist with id " + artistID + " exists.");
        }

        Act act = new Act(idGenerator, artist, title, minutesDuration);
        state.getActRepository().add(act);
        return act.getId();
    }

    public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {
    	if(actID == null) {
    		return error("actID is null!");
    	}
    	if(theatreID == null) {
    		return error("theatreID is null!");
    	}
    	
    	if(actID.isEmpty()) {
    		return error("actID is empty!");
    	}
    	if(theatreID.isEmpty()) {
    		return error("theatreID is empty!");
    	}
    	
    	Act act = state.getActRepository().getById(actID);
    	Theatre theatre = state.getTheatreRepository().getById(theatreID);
    	
    	if(act == null) {
    		return error("No act with id " + actID);
    	}
    	if(theatre == null) {
    		return error("No theatre with id " + theatreID);
    	}
    	
    	try {
    		Performance performance = new Performance(idGenerator, act, theatre, startTimeStr, premiumPriceStr, cheapSeatsStr);
    		state.getPerformanceRepository().add(performance);
    		return performance.getId();
    	} catch(TBSException e) {
    		return error(e.getMessage());
    	}
    }

    public String issueTicket(String performanceID, int rowNumber, int seatNumber) {
        if(performanceID == null) {
            return error("performanceID is null!");
        }
        if(performanceID.isEmpty()) {
            return error("performanceID is empty!");
        }
        if(!idGenerator.isValidID(performanceID)) {
            return error("performanceID is not a valid ID.");
        }

        Performance performance = state.getPerformanceRepository().getById(performanceID);
        if (performance == null) {
            return error("No performance exists with id " + performanceID);
        }

        Ticket ticket;
        try {
            ticket = performance.issueTicket(rowNumber, seatNumber);
        } catch(TBSException e) {
            return error(e.getMessage());
        }

        return ticket.getId();
    }

    public List<String> seatsAvailable(String performanceID) {
        if (performanceID == null) {
            return listError("performanceID is null!");
        }
        if (performanceID.isEmpty()) {
            return listError("performanceID is empty!");
        }
        if (!idGenerator.isValidID(performanceID)) {
            return listError("performanceID is not a valid id!");
        }

        Performance performance = state.getPerformanceRepository().getById(performanceID);
        if(performance == null) {
            return listError("No performance exists with id " + performanceID);
        }

        List<Seat> seats = performance.getAvailableSeats();
        List<String> output = new ArrayList<>();
        for(Seat seat : seats) {
            output.add(seat.toString());
        }

        return output;
    }

    public List<String> salesReport(String actID) {
        if(actID == null) {
            return listError("actID is null!");
        }
        if(actID.isEmpty()) {
            return listError("actID is empty!");
        }
        if(!idGenerator.isValidID(actID)) {
            return listError("actID is not a valid id!");
        }

        Act act = state.getActRepository().getById(actID);
        if(act == null) {
            return listError("No act exists with id " + actID);
        }

        try {
            return salesReportFormatter.formatSalesReport(act);
        } catch(TBSException e) {
            return listError(e.getMessage());
        }
    }

    public List<String> dump() {
        return Collections.singletonList("Theatre Booking System 1.0 brought to you using Oracle\u00ae PeopleSoft\u2122");
    }

    private static String error(String message) {
        return "ERROR " + message;
    }

    private static List<String> listError(String message) {
        return Collections.singletonList(error(message));
    }
}
