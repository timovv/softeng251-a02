package tbs.server;

/**
 * A seat in a theatre.
 */
public class Seat {
    private static final String TO_STRING_FORMAT = "%d\t%d";
    private final boolean isPremium;
    private final int rowNumber, seatNumber;

    public Seat(int rowNumber, int seatNumber, boolean isPremium) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.isPremium = isPremium;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }


    public boolean isPremiumSeat() {
    	return isPremium;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, rowNumber, seatNumber);
    }
}
