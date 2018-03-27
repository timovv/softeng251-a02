package tbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class TheatreParser {

    // -- Constants --
    private static final Pattern THEATRE_EXPRESSION = Pattern.compile("^THEATRE\t(.+)\t(\\d+)\t(\\d+)$");
    private static final int ID_INDEX = 1;
    private static final int SEATING_DIMENSION_INDEX = 2;
    private static final int FLOOR_AREA_INDEX = 3;

    private final Scanner scanner;

    public TheatreParser(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    public TheatreParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Theatre> readAllRemaining() {
        List<Theatre> out = new ArrayList<>();

        while(scanner.hasNextLine()) {
            out.add(getNextTheatre());
        }

        return out;
    }

    public Theatre getNextTheatre() {
        scanner.findInLine(THEATRE_EXPRESSION);
        MatchResult result = scanner.match();
        if(result.groupCount() == 0) {
           throw new RuntimeException("Encountered invalid theatre: " + result.toString());
        }

        String id = result.group(ID_INDEX);
        int seatingDimesion = Integer.parseInt(result.group(SEATING_DIMENSION_INDEX));
        int floorArea = Integer.parseInt(result.group(FLOOR_AREA_INDEX));

        return new Theatre(id, seatingDimesion, floorArea);
    }
}
