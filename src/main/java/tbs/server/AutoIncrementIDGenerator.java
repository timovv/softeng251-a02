package tbs.server;

import java.util.regex.Pattern;

/**
 * An IDGenerator that increments a counter every time a new ID is generated, and returns the value of that
 * counter.
 */
public class AutoIncrementIDGenerator implements IDGenerator {

    private static final Pattern IS_NUMERIC_PATTERN = Pattern.compile("\\d+");

    private int currentId = 0;

    @Override
    public String createUniqueID() {
        return Integer.toString(currentId++);
    }

    @Override
    public boolean isValidID(String id) {
        return IS_NUMERIC_PATTERN.matcher(id).matches();
    }
}
