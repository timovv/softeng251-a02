package tbs.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A performance in the Theatre Booking System.
 *
 * Each performance is of an act, and is performed in a specific theatre at a specific start time.
 */
public class Performance implements Identifiable {

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
        this.idGenerator = idGenerator;
        this.id = idGenerator.createUniqueId();
        this.act = act;
        this.theatre = theatre;
        this.startTime = startTime;
        this.premiumSeatPrice = premiumSeatPrice;
        this.cheapSeatPrice = cheapSeatPrice;
    }

    private static int parsePriceString(String price) {
        if(price.charAt(0) != '$') {
            throw new TBSException("Invalid price string format.");
        }

        try {
            return Integer.parseInt(price.substring(1));
        } catch(NumberFormatException nex) {
            throw new TBSException("Invalid price string format.", nex);
        }
    }

    public Ticket issueTicket(int rowNumber, int seatNumber) {
        if (rowNumber < 1 || seatNumber < 1 || rowNumber > theatre.getSeatingDimension()
                || seatNumber > theatre.getSeatingDimension()) {
            throw new TBSException("Seat is out of bounds for this theatre");
        }
        
        Seat seat = theatre.getSeatAt(rowNumber, seatNumber);
        
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
    
    private int calculatePriceForSeat(Seat seat) {
    	if(seat.isPremiumSeat()) {
    		return premiumSeatPrice;
    	} else {
    		return cheapSeatPrice;
    	}
    }
    
    // GETTERS AND SETTERS

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
