package tbs.server;

public class Act implements Identifiable {

    private final String id;
    private final String title;
    private final int minutesDuration;
    private final Artist artist;

    public Act(IDGenerator idGenerator, Artist artist, String title, int minutesDuration) {
        this.minutesDuration = minutesDuration;
        this.artist = artist;
        this.title = title;
        this.id = idGenerator.createUniqueId();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMinutesDuration() {
        return minutesDuration;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Act)
                && ((Act) other).getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public Artist getArtist() {
        return artist;
    }
}
