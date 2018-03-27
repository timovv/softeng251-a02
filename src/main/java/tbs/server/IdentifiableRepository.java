package tbs.server;

import java.util.*;

public class IdentifiableRepository<T extends Identifiable> implements Iterable<Map.Entry<String, T>> {

    private final Map<String, T> values = new HashMap<>();

    public IdentifiableRepository() {
    }

    public IdentifiableRepository(Collection<T> startingValues) {
        for(T value : startingValues) {
            values.put(value.getId(), value);
        }
    }

    public void add(T toAdd) {
        if(values.containsKey(toAdd.getId())) {
            throw new IllegalArgumentException("An object with the same ID already exists in the repository.");
        }

        values.put(toAdd.getId(), toAdd);
    }

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
     * Get all IDs corresponding to values stored in this IdentifiableRepository.
     * @return
     */
    public List<String> getAllIDs() {
        return Collections.unmodifiableList(new ArrayList<>(values.keySet()));
    }

    public boolean contains(T t) {
        return values.containsKey(t.getId());
    }

    public boolean hasItemOfId(String id) {
        return values.containsKey(id);
    }

    @Override
    public Iterator<Map.Entry<String, T>> iterator() {
        return values.entrySet().iterator();
    }
}
