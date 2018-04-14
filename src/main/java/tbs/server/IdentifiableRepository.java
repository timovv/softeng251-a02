package tbs.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A repository of identifiable objects.
 *
 * An IdentifiableRepository is a collection of objects which implement the Identifiable interface. It ensures that
 * no objects in the repository have the same ID, and also provides helper methods which allow for easy access
 * to a collection of all IDs in the repository.
 * @param <T> The type of identifiable object which is stored in this type of IdentifiableRepository.
 */
public class IdentifiableRepository<T extends Identifiable> implements Iterable<T> {

    private final Map<String, T> values = new HashMap<>();

    /**
     * Construct a new, empty, IdentifiableRepository.
     */
    public IdentifiableRepository() {
    }

    /**
     * Construct a new IdentifiableRepository with the given starting values.
     * @param startingValues The starting values to place in this IdentifiableRepository.
     * @throws TBSException if any of the values in startingValues have the same ID.
     */
    public IdentifiableRepository(Collection<T> startingValues) {
        for(T value : startingValues) {
            add(value);
        }
    }

    /**
     * Add a new item to this IdentifiableRepository.
     * @param toAdd The item to add.
     * @throws TBSException if there already exists an item in this repository with the same ID as the item being added.
     */
    public void add(T toAdd) {
        if(values.containsKey(toAdd.getId())) {
            throw new TBSException("An object with the same ID already exists in the repository.");
        }

        values.put(toAdd.getId(), toAdd);
    }

    /**
     * Add all the given items to this IdentifiableRepository.
     * @param toAdd The items to add.
     * @throws TBSException if any of the items to be added have the same ID as each other or any items already in the
     * repository.
     */
    public void addAll(Collection<T> toAdd) {
        for(T add : toAdd) {
            add(add);
        }
    }

    /**
     * Get an object by its id.
     * @param id The id for which to find an object with that id.
     * @return The found object, or null if none was found.
     */
    public T getById(String id) {
        return values.get(id);
    }

    /**
     * Get all values stored in this IdentifiableRepository.
     * @return An unmodifiable list of all values stored in this IdentifiableRepository.
     */
    public List<T> getAllValues() {
        return Collections.unmodifiableList(new ArrayList<>(values.values()));
    }

    /**
     * @return all IDs corresponding to values stored in this IdentifiableRepository.
     */
    public List<String> getAllIDs() {
        return Collections.unmodifiableList(new ArrayList<>(values.keySet()));
    }

    /**
     * @return true if this IdentifiableRepository already has an item with the given ID.
     */
    public boolean hasItemOfId(String id) {
        return values.containsKey(id);
    }

    @Override
    public Iterator<T> iterator() {
        return values.values().iterator();
    }
}
