package tbs.server;

/**
 * A seat in a theatre.
 * This class is immutable.
 * @see Theatre
 * @author Timo van Veenendaal
 */
public class Seat {
    private static final String TO_STRING_FORMAT = "%d\t%d";
    private final boolean isPremium;
    private final int rowNumber, seatNumber;

    /**
     * Create a new Seat with the given row number, seat number, and premium status.
     * @param rowNumber The seat's row number.
     * @param seatNumber The number of the seat in its row.
     * @param isPremium Whether this seat is premium, i.e. whether a higher price should be paid for this seat.
     */
    public Seat(int rowNumber, int seatNumber, boolean isPremium) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.isPremium = isPremium;
    }

    /**
     * @return The seat's row number.
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * @return The number of this seat in its row.
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * A seat is 'premium' if it costs a higher price than the other, 'cheap' seats.
     * @return true if the seat is premium, false otherwise.
     */
    public boolean isPremiumSeat() {
    	return isPremium;
    }

    /**
     * A human-readable representation of this seat's row number and seat number.
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, rowNumber, seatNumber);
    }
}
