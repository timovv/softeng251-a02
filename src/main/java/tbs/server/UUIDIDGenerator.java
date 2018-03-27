package tbs.server;

import java.util.UUID;

public class UUIDIDGenerator implements IDGenerator {
    @Override
    public String createUniqueId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean isValidId(String id) {
        // This is an ugly hack but it's a good surefire way to check
        // a UUID is valid.
        try {
            //noinspection ResultOfMethodCallIgnored
            UUID.fromString(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
