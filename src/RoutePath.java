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

    /**
     * @return the priority queue of tracks needed to complete this route
     */
    public PriorityQueue<Track> getTracks() {
        return tracks;
    }

    /**
     * Removes a track from the priority queue
     * @param track the track to remove
     */
    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    /**
     * Adds a track to the priority queue
     * @param track the track to add
     */
    public void addTrack(Track track) {
        tracks.add(track);
    }

    public int compareTo(RoutePath path2) {
        return path2.pointValue - this.pointValue;
    }
}