package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActRepository extends IdentifiableRepository<Act> {

    public List<String> getActIdsForArtist(Artist artist) {
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
