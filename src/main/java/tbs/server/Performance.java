package tbs.server;

import java.util.ArrayList;
import java.util.List;

public class Performance implements Identifiable {

    private final IDGenerator idGenerator;
    private final String id;
    private final Act act;
    private final Theatre theatre;
    private final String startTime;
    private final int premiumSeatPrice, cheapSeatPrice;

    private final Ticket[][] tickets;

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
        this.tickets = new Ticket[theatre.getSeatingDimension()][theatre.getSeatingDimension()];
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

        if (tickets[rowNumber - 1][seatNumber - 1] != null) {
            throw new TBSException("Seat [" + rowNumber + ", " + seatNumber + "] is already taken.");
        }

        Ticket ticket = new Ticket(idGenerator);
        tickets[rowNumber - 1][seatNumber - 1] = ticket;
        return ticket;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> output = new ArrayList<>();
        for(Seat seat : theatre.getSeats()) {
            if(tickets[seat.getRowNumber() - 1][seat.getSeatNumber() - 1] == null) {
                output.add(seat);
            }
        }

        return output;
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
