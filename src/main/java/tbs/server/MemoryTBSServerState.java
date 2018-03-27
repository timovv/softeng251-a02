package tbs.server;

public class MemoryTBSServerState implements TBSServerState {

    private final IdentifiableRepository<Theatre> theatres;
    private final PerformanceRepository performances;
    private final ArtistRepository artists;
    private final ActRepository acts;
    private final TicketRepository tickets;

    public MemoryTBSServerState() {
        this(
                new IdentifiableRepository<Theatre>(),
                new PerformanceRepository(),
                new ArtistRepository(),
                new ActRepository(),
                new TicketRepository()
        );
    }

    public MemoryTBSServerState(IdentifiableRepository<Theatre> theatres, PerformanceRepository performances,
                                ArtistRepository artists, ActRepository acts, TicketRepository tickets) {
        this.theatres = theatres;
        this.performances = performances;
        this.artists = artists;
        this.acts = acts;
        this.tickets = tickets;
    }

    @Override
    public IdentifiableRepository<Theatre> getTheatreRepository() {
        return theatres;
    }

    @Override
    public PerformanceRepository getPerformanceRepository() {
        return performances;
    }

    @Override
    public ArtistRepository getArtistRepository() {
        return artists;
    }

    @Override
    public ActRepository getActRepository() {
        return acts;
    }

    @Override
    public TicketRepository getTicketRepository() {
        return tickets;
    }
}
