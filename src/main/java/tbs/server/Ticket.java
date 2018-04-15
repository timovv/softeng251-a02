package tbs.server;

/**
 * An issued ticket in the Theatre Booking System.
 */
public class Ticket implements Identifiable {

    private final String id;
    private final int price;

    public Ticket(IDGenerator idGenerator, int price) {
        this.id = idGenerator.createUniqueID();
        this.price = price;
    }

    @Override
    public String getId() {
        return id;
    }
    
    public int getPrice() {
    	return price;
    }
}
