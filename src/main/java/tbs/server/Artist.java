package tbs.server;

import java.util.Objects;

/**
 * An artist in the theatre booking system.
 */
public class Artist implements Identifiable {
    private final String id;
    private final String name;

    public Artist(IDGenerator idGenerator, String name) {
        this.id = idGenerator.createUniqueID();
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }
}
