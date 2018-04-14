package tbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheatreParser {

    // -- Constants --
    private static final Pattern THEATRE_EXPRESSION = Pattern.compile("^THEATRE\t(.+)\t(\\d+)\t(\\d+)$");
    private static final int ID_INDEX = 1;
    private static final int SEATING_DIMENSION_INDEX = 2;
    private static final int FLOOR_AREA_INDEX = 3;
    
    private final Scanner scanner;
    private final CustomIDGenerator customIDGenerator = new CustomIDGenerator();

    public TheatreParser(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    public List<Theatre> readAllRemaining() {
        List<Theatre> out = new ArrayList<>();

        Theatre next;
        while((next = getNextTheatre()) != null) {
            out.add(next);
        }

        return out;
    }

    public Theatre getNextTheatre() {
        if(!scanner.hasNextLine()) {
            return null;
        }

        String line = scanner.nextLine();
        Matcher result = THEATRE_EXPRESSION.matcher(line);

        if(!result.matches()) {
            throw new TBSException("Encountered invalid theatre definition: " + line);
        }

        String id = result.group(ID_INDEX);
        int seatingDimension = Integer.parseInt(result.group(SEATING_DIMENSION_INDEX));
        int floorArea = Integer.parseInt(result.group(FLOOR_AREA_INDEX));

        customIDGenerator.setNextID(id);
        return new Theatre(customIDGenerator, seatingDimension, floorArea);
    }
}
