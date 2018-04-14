package tbs.server;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitialiseTest {

    @Test
    void initialiseWorksCorrectly() {
        TBSServer server = new TBSServerImpl();
        String result = TestUtil.initialiseTestData(server);
        assertEquals(result, "");
        List<String> theatreIds = server.getTheatreIDs();
        assertEquals(theatreIds.get(0), "T1");
        assertEquals(theatreIds.get(1), "T2");
        assertEquals(theatreIds.get(2), "T3");
        assertEquals(theatreIds.size(), 3);
    }

    @Test
    void intialiseErrorsOnInvalid() {
        String fileName = "src/test/resources/theatres_fake.csv";
        TBSServer server = new TBSServerImpl();
        String result = server.initialise(fileName);
        assertTrue(result.startsWith("ERROR "));
    }
}
