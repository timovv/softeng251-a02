package tbs.server;

/**
 * An exception representing exceptional behaviour in the Theatre Booking System.
 */
public class TBSException extends RuntimeException {

    /**
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new TBSException with the given message.
     * @param message A message providing detail about why this TBSException was thrown.
     */
	public TBSException(String message) {
        super(message);
    }

    /**
     * Construct a new TBSException with the given message, and inner exception.
     * @param message A message providing detail about why this TBSException was thrown.
     * @param innerException The cause of this exception, i.e. another exception that led
     *                       this TBSException to be thrown.
     */
    public TBSException(String message, Exception innerException) {
        super(message, innerException);
    }
}
