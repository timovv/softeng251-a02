package tbs.server;

import java.util.Objects;

/**
 * Implementation of TBSServerState based in memory.
 */
public class MemoryTBSServerState implements TBSServerState {

    private final IdentifiableRepository<Theatre> theatres;
    private final PerformanceRepository performances;
    private final ArtistRepository artists;
    private final ActRepository acts;

    /**
     * Construct a new MemoryTBSServerState using default implementations for the repositories.
     */
    public MemoryTBSServerState() {
        this(
                new IdentifiableRepository<Theatre>(),
                new PerformanceRepository(),
                new ArtistRepository(),
                new ActRepository()
        );
    }

    /**
     * Construct a new MemoryTBSServerState using the given repositories.
     * @param theatres The theatre repository this MemoryTBSServerState should use.
     * @param performances The PerformanceRepository this MemoryTBSServerState should use.
     * @param artists The ArtistRepository this MemoryTBSServerState should use.
     * @param acts The ActRepository this MemoryTBSServerState should use.
     */
    public MemoryTBSServerState(IdentifiableRepository<Theatre> theatres, PerformanceRepository performances,
                                ArtistRepository artists, ActRepository acts) {
        this.theatres = Objects.requireNonNull(theatres);
        this.performances = Objects.requireNonNull(performances);
        this.artists = Objects.requireNonNull(artists);
        this.acts = Objects.requireNonNull(acts);
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
}
