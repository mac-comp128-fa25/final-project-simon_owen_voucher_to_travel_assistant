import java.util.PriorityQueue;
import java.util.List;

public class RoutePath implements Comparable<RoutePath>{

    private PriorityQueue<Track> tracks;
    private int points;

    public RoutePath(List<Track> unorganizedTracks, int points) {
        this.points = points;
        tracks = new PriorityQueue<>(new TrackComparator());
        for(Track track : unorganizedTracks) {
            tracks.add(track);
        }
    }

    public int getPoints() {
        return points;
    }

    public PriorityQueue<Track> getTracks() {
        return tracks;
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public int compareTo(RoutePath path2) {
        return path2.points - points;
    }
}