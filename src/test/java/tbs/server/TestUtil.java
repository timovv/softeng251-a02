package tbs.server;

public class TestUtil {

    public static String initialiseTestData(TBSServer server) {
        String fileName = "src/test/resources/theatres1.csv";
        return server.initialise(fileName);
    }
}
