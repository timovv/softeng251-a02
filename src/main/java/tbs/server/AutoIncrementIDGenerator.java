package tbs.server;

public class AutoIncrementIDGenerator implements IDGenerator {
    private int currentId = 0;

    @Override
    public String createUniqueId() {
        return Integer.toString(currentId++);
    }

    @Override
    public boolean isValidId(String id) {
        return id.matches("\\d+");
    }
}
