import java.util.PriorityQueue;
import java.util.List;

public class RoutePath extends RouteCard implements Comparable<RoutePath>{

    private PriorityQueue<Track> tracks;

    public RoutePath(City startCity, City endCity, List<Track> unorganizedTracks, int points) {
        super(startCity, endCity, points);
        tracks = new PriorityQueue<>(new TrackComparator());
        for(Track track : unorganizedTracks) {
            tracks.add(track);
        }
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
        return path2.pointValue - this.pointValue;
    }
}