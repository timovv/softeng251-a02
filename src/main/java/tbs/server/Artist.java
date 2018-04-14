package tbs.server;

import java.util.Objects;

/**
 * An artist in the theatre booking system.
 */
public class Artist implements Identifiable {
    private final String id;
    private final String name;

    /**
     * Construct a new artist with the given parameters.
     * @param idGenerator An ID generator used to generate the artist's unique ID.
     * @param name The name of the artist.
     */
    public Artist(IDGenerator idGenerator, String name) {
        this.id = idGenerator.createUniqueID();
        this.name = Objects.requireNonNull(name);
    }

    /**
     * @return The name of this artist.
     */
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }
}
