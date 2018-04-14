package tbs.server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetActIDsForArtistTest {

    @Test
    void worksCorrectlyNormally() {
        TBSServer server = new TBSServerImpl();
        String artistId = server.addArtist("John Cena");
        String act1 = server.addAct("Act 1", artistId, 1);
        String act2 = server.addAct("Act 2", artistId, 1);
        String act3 = server.addAct("Act 3", artistId, 1);

        List<String> expectedIds = new ArrayList<>();
        expectedIds.add(act1);
        expectedIds.add(act2);
        expectedIds.add(act3);
        Collections.sort(expectedIds);

        List<String> output = server.getActIDsForArtist(artistId);

        for(int i = 0; i < expectedIds.size(); ++i) {
            assertEquals(output.get(i), expectedIds.get(i));
        }
    }

    @Test
    void errorOnEmptyArtistID() {
        TBSServer server = new TBSServerImpl();
        String artistId = server.addArtist("John Cena");
        String act = server.addAct("Act 1", artistId, 1);

        List<String> output = server.getActIDsForArtist("");
        assertEquals(1, output.size());
        assertTrue(output.get(0).startsWith("ERROR "));
    }

    @Test
    void errorOnNonExistentArtistID() {
        TBSServer server = new TBSServerImpl();
        String artistId = server.addArtist("John Cena");
        String act = server.addAct("Act 1", artistId, 1);

        List<String> output = server.getActIDsForArtist("FAKEID dont exist lmao");
        assertEquals(1, output.size());
        assertTrue(output.get(0).startsWith("ERROR "));
    }
}
