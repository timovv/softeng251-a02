package tbs.server;

public interface IDGenerator {
    /**
     * Generate a new unique identifier.
     * @return The created unique identifier, as a string.
     */
    String createUniqueId();

    boolean isValidId(String id);
}
