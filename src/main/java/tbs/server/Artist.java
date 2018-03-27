package tbs.server;

import java.util.Collection;
import java.util.List;

public class Artist implements Identifiable {
    private final String id;
    private final String name;

    public Artist(IDGenerator idGenerator, String name) {
        this.id = idGenerator.createUniqueId();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Artist)
                && ((Artist) other).getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
