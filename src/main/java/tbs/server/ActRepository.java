package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A repository of acts in the theatre booking system.
 */
public class ActRepository extends IdentifiableRepository<Act> {

    /**
     * @return A list of all act IDs belonging to the given artist, in their natural order.
     */
    public List<String> getActIdsForArtist(Artist artist) {
        Objects.requireNonNull(artist);

        List<String> output = new ArrayList<>();
        for(Act act : getAllValues()) {
            if(artist.getId().equals(act.getArtist().getId())) {
                output.add(act.getId());
            }
        }

        Collections.sort(output);
        return output;
    }
}
