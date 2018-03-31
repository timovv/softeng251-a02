package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A collection of artists in the Theatre Booking System.
 * {@inheritDoc}
 * @author Timo van Veenendaal
 */
public class ArtistRepository extends IdentifiableRepository<Artist> {

    /**
     * Get the names of all artists in this ArtistRepository.
     * @return A list of the names of all artists in the repository in alphabetical order.
     */
    public List<String> getArtistNames() {
        List<String> out = new ArrayList<>();
        for(Artist artist : getAllValues()) {
            out.add(artist.getName());
        }

        Collections.sort(out);
        return Collections.unmodifiableList(out);
    }

    /**
     * {@inheritDoc}
     * @throws TBSException if an artist already exists in the repository with the same name (case-insensitive).
     */
    @Override
    public void add(Artist artist) {
        for(Artist other : getAllValues()) {
            if(other.getName().equalsIgnoreCase(artist.getName())) {
                throw new TBSException("Artist with this name already exists");
            }
        }

        super.add(artist);
    }
}
