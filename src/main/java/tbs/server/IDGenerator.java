package tbs.server;

public interface IDGenerator {
    /**
     * Generate a new unique identifier.
     * @return The created unique identifier, as a string.
     */
    String createUniqueID();

    /**
     * Determine whether the given ID could be a valid ID generated by this generator.
     * @param id The ID to test.
     * @return true if the ID is valid, false otherwise.
     */
    boolean isValidID(String id);
}
