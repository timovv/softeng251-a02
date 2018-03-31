package tbs.server;

/**
 * Objects that implement this interface have a unique ID, which is unique to all objects of that type.
 */
public interface Identifiable {

    /**
     * Get a unique identifier representing this object. The identifier must not change
     * over the object's lifetime, and must be unique to all objects of this type.
     * @return This object's unique identifier.
     */
    String getId();
}
