package tbs.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A performance in the Theatre Booking System.
 *
 * Each performance is of an act, and is performed in a specific theatre at a specific start time.
 */
public class Performance implements Identifiable {

    private static final NumberFormat PRICE_STRING_FORMAT = new DecimalFormat("$0");

    private final IDGenerator idGenerator;
    private final String id;
    private final Act act;
    private final Theatre theatre;
    private final String startTime;
    private final int premiumSeatPrice, cheapSeatPrice;
    
    private final Map<Seat, Ticket> tickets = new HashMap<>();

    public Performance(IDGenerator idGenerator, Act act, Theatre theatre, String startTime, String premiumPriceStr,
                       String cheapSeatsStr) {
        this(
                idGenerator,
                act,
                theatre,
                startTime,
                parsePriceString(premiumPriceStr),
                parsePriceString(cheapSeatsStr)
        );
    }

    public Performance(IDGenerator idGenerator, Act act, Theatre theatre, String startTime, int premiumSeatPrice, int cheapSeatPrice) {
        this.idGenerator = Objects.requireNonNull(idGenerator);
        this.id = idGenerator.createUniqueID();
        this.act = Objects.requireNonNull(act);
        this.theatre = Objects.requireNonNull(theatre);
        this.startTime = Objects.requireNonNull(startTime);
        this.premiumSeatPrice = premiumSeatPrice;
        this.cheapSeatPrice = cheapSeatPrice;
    }

    private static int parsePriceString(String s) {
        try {
            return PRICE_STRING_FORMAT.parse(s).intValue();
        } catch(Exception ex) {
            throw new TBSException("Invalid price string format", ex);
        }
    }

    public Ticket issueTicket(int rowNumber, int seatNumber) {
        Seat seat = theatre.getSeatAt(rowNumber, seatNumber);

        if(seat == null) {
            throw new TBSException("This theatre does not have seat with row " + rowNumber + " and seat " + seatNumber);
        }

        if (tickets.get(theatre.getSeatAt(rowNumber, seatNumber)) != null) {
            throw new TBSException("Seat [" + rowNumber + ", " + seatNumber + "] is already taken.");
        }

        Ticket ticket = new Ticket(idGenerator, calculatePriceForSeat(seat));
        tickets.put(seat, ticket);
        return ticket;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> output = new ArrayList<>();
        for(Seat seat : theatre.getSeats()) {
            if(tickets.get(seat) == null) {
                output.add(seat);
            }
        }

        return output;
    }
    
    public List<Ticket> getIssuedTickets() {
    	return new ArrayList<>(tickets.values());
    }

    public List<String> getIssuedTicketIds() {
        ArrayList<String> output = new ArrayList<>();
        for(Ticket ticket : tickets.values()) {
            output.add(ticket.getId());
        }

        return output;
    }
    
    private int calculatePriceForSeat(Seat seat) {
    	if(seat.isPremiumSeat()) {
    		return getPremiumSeatPrice();
    	} else {
    		return getCheapSeatPrice();
    	}
    }
    
    @Override
    public String getId() {
        return id;
    }

    public Act getAct() {
        return act;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getPremiumSeatPrice() {
        return premiumSeatPrice;
    }

    public int getCheapSeatPrice() {
        return cheapSeatPrice;
    }
}
