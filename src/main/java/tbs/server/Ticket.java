package tbs.server;

public class Ticket implements Identifiable {

    private final String id;

    public Ticket(IDGenerator idGenerator) {
        this.id = idGenerator.createUniqueId();
    }

    /**
     * Get the unique ID associated with this Ticket.
     * @return the unique ID associated with this ticket.
     */
    @Override
    public String getId() {
        return this.id;
    }
}
