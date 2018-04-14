package tbs.server;

/**
 * A class representing generic exceptional behaviour in the Theatre Booking System.
 */
public class TBSException extends RuntimeException {

    /**
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 201804L;

	public TBSException(String message) {
        super(message);
    }

    public TBSException(String message, Exception innerException) {
        super(message, innerException);
    }
}
