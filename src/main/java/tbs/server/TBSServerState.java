package tbs.server;

public interface TBSServerState {
    IdentifiableRepository<Theatre> getTheatreRepository();
    PerformanceRepository getPerformanceRepository();
    ArtistRepository getArtistRepository();
    ActRepository getActRepository();
    TicketRepository getTicketRepository();
}
