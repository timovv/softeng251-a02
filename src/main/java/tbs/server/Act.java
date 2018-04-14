package tbs.server;

import java.util.Objects;

/**
 * A representation of an act in the theatre booking system.
 *
 * Each act has an ID, a title, a duration in minutes, and is performed by one {@link Artist}.
 */
public class Act implements Identifiable {

    private final String id;
    private final String title;
    private final int minutesDuration;
    private final Artist artist;

    /**
     * Construct a new act with the given parameters.
     * @param idGenerator An IDGenerator used to generate the act's unique ID.
     * @param artist The artist who performs the act.
     * @param title The title of the act.
     * @param minutesDuration The duration of the act in minutes.
     */
    public Act(IDGenerator idGenerator, Artist artist, String title, int minutesDuration) {
        this.minutesDuration = minutesDuration;
        this.artist = Objects.requireNonNull(artist);
        this.title = Objects.requireNonNull(title);
        this.id = idGenerator.createUniqueID();
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * @return The title of this act.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The duration of this act, in minutes.
     */
    public int getMinutesDuration() {
        return minutesDuration;
    }

    /**
     * @return The artist who performs this act.
     */
    public Artist getArtist() {
        return artist;
    }
}
