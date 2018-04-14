package tbs.server;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * An IDGenerator which makes users set the next ID to be generated themselves.
 * CustomIDGenerator allows IDs to be input from an external source
 * while also ensuring that all IDs generated are still unique.
 */
public class CustomIDGenerator implements IDGenerator {

    private Set<String> previousIDs = new HashSet<>();
    private String nextID = null;

    /**
     * Construct a new CustomIDGenerator.
     */
    public CustomIDGenerator() {
    }

    /**
     * Get the next ID. An ID must have been previously set using {@link CustomIDGenerator#setNextID(String)}.
     * {@inheritDoc}
     * @throws TBSException if setNextID has not been called between now and the previous call to this method.
     */
    @Override
    public String createUniqueID() {
        if(nextID == null) {
            throw new TBSException("The next ID has not been set.");
        }

        String id = nextID;
        nextID = null;
        previousIDs.add(id);
        return id;
    }

    /**
     * For CustomIDGenerator, all possible strings could be valid IDs.
     * @return true
     */
    @Override
    public boolean isValidID(String id) {
        return true;
    }

    /**
     * Set the next ID that this CustomIDGenerator should output.
     * @param id The next ID to set, which must not be null, and also cannot have been used as an ID by this IDGenerator
     *           previously.
     * @throws NullPointerException if id is null.
     * @throws TBSException if the id being set has already been used as an ID by this IDGenerator.
     */
    public void setNextID(String id) {
        if(previousIDs.contains(id)) {
            throw new TBSException("An object has already been created with id " + id);
        }

        nextID = Objects.requireNonNull(id);
    }
}
