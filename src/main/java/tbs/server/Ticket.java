package tbs.server;

/**
 * An issued ticket in the Theatre Booking System.
 */
public class Ticket implements Identifiable {

    private final String id;
    private final int price;

    /**
     * Construct a new ticket.
     * @param idGenerator An IDGenerator to be used to generate this Ticket's ID.
     * @param price The price of the ticket in dollars.
     */
    public Ticket(IDGenerator idGenerator, int price) {
        this.id = idGenerator.createUniqueID();
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
