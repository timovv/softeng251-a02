package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A theatre.
 */
public class Theatre implements Identifiable {
    private final String id;
    private final int seatingDimension;
    private final int floorArea;
    private final List<Seat> seats;

    public Theatre(IDGenerator idGenerator, int seatingDimension, int floorArea) {
    	this.id = idGenerator.createUniqueID();
        this.seatingDimension = seatingDimension;
        this.floorArea = floorArea;

        this.seats = new ArrayList<>();
        for(int i = 0; i < seatingDimension; ++i) {
            for(int j = 0; j < seatingDimension; ++j) {
                seats.add(new Seat(i + 1, j + 1, isPremiumSeatLocation(i + 1, j + 1)));
            }
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public int getSeatCount() {
        return seatingDimension * seatingDimension;
    }

    public int getSeatingDimension() {
        return seatingDimension;
    }
    
    public int getFloorArea() {
        return floorArea;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }
    
    public Seat getSeatAt(int row, int seatNumber) {
    	return seats.get((row - 1) * seatingDimension + (seatNumber - 1));
    }
    
    protected boolean isPremiumSeatLocation(int row, int seatNumber) {
        return (row * seatingDimension) + seatNumber <= getSeatCount() / 2;
    }
}
