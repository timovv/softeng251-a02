package tbs.server;

/**
 * An issued ticket in the Theatre Booking System.
 */
public class Ticket implements Identifiable {

    private final String id;
    private final int price;

    /**
     * Construct a new ticket.
     * @param idGenerator The
     * @param price
     */
    public Ticket(IDGenerator idGenerator, int price) {
        this.id = idGenerator.createUniqueId();
        this.price = price;
    }

    /**
     * Get the unique ID associated with this Ticket. This 
     * {@inheritDoc}
     * @return the unique ID associated with this ticket.
     */
    @Override
    public String getId() {
        return id;
    }
    
    public int getPrice() {
    	return price;
    }
}
