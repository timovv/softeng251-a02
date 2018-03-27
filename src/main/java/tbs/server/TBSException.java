package tbs.server;

public class TBSException extends RuntimeException {
    public TBSException(String message) {
        super(message);
    }

    public TBSException(String message, Exception innerException) {
        super(message, innerException);
    }
}
