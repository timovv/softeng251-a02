package tbs.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddArtistTest {
    @Test
    void addArtistAddsArtistCorrectly() {
        TBSServer server = new TBSServerImpl();
        String id = server.addArtist("John");
        assertEquals(server.getArtistIDs().size(), 1);
        assertEquals(id, server.getArtistIDs().get(0));
    }

    @Test
    void addArtistFailsIfArtistAlreadyExists() {
        TBSServer server = new TBSServerImpl();
        String id = server.addArtist("John");
        String id2 = server.addArtist("John");
        assertTrue(!id.startsWith("ERROR "));
        assertTrue(id2.startsWith("ERROR "));
    }

    @Test
    void addArtistFailsIfArtistAlreadyExistsCaseInsensitive() {
        TBSServer server = new TBSServerImpl();
        String id = server.addArtist("John");
        String id2 = server.addArtist("JOHN");
        assertTrue(!id.startsWith("ERROR "));
        assertTrue(id2.startsWith("ERROR "));
    }

    @Test
    void addArtistIdIsUnique() {
        TBSServer server = new TBSServerImpl();
        String id = server.addArtist("Timo");
        String id2 = server.addArtist("john");
        assertNotEquals(id, id2);
    }

    @Test
    void addArtistFailsIfNameIsEmpty() {
        TBSServer server = new TBSServerImpl();
        String result = server.addArtist("");
        assertTrue(result.startsWith("ERROR "));
    }
}
