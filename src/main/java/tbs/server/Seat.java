package tbs.server;

public class Seat {
    private final String TO_STRING_FORMAT = "%d\t%d";

    private final int rowNumber, seatNumber;

    public Seat(int rowNumber, int seatNumber) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, rowNumber, seatNumber);
    }
}
