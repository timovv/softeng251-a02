package tbs.server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetArtistIDsTest {
    @Test
    void getArtistIDsReturnsCorrectIDs() {
        TBSServer server = new TBSServerImpl();
        String id1 = server.addArtist("John");
        String id2 = server.addArtist("Timo");
        String id3 = server.addArtist("TEST");

        List<String> ids = server.getArtistIDs();

        assertEquals(ids.size(), 3);
        assertTrue(ids.contains(id1));
        assertTrue(ids.contains(id2));
        assertTrue(ids.contains(id3));
    }

    @Test
    void getArtistIDsReturnsIdsInAlphabeticalOrder() {
        TBSServer server = new TBSServerImpl();
        String id1 = server.addArtist("John");
        String id2 = server.addArtist("Timo");
        String id3 = server.addArtist("TEST");

        List<String> ids = server.getArtistIDs();

        List<String> testAgainst = new ArrayList<>();
        testAgainst.add(id1);
        testAgainst.add(id2);
        testAgainst.add(id3);

        Collections.sort(testAgainst);

        for(int i = 0; i < 3; ++i) {
            assertEquals(testAgainst.get(i), ids.get(i));
        }
    }
}
