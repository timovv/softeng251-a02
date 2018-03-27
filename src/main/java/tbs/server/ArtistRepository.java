package tbs.server;

import java.util.LinkedList;
import java.util.List;

public class ArtistRepository extends IdentifiableRepository<Artist> {

    public List<String> getArtistNames() {
        List<String> out = new LinkedList<>();
        for(Artist artist : getAllValues()) {
            out.add(artist.getName());
        }

        return out;
    }

    @Override
    public void add(Artist artist) {
        for(Artist other : getAllValues()) {
            if(other.getName().equalsIgnoreCase(artist.getName())) {
                throw new TBSException("Artist not found");
            }
        }

        super.add(artist);
    }
}
