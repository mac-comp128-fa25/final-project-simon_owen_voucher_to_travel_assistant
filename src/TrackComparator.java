import java.util.Comparator;

public class TrackComparator implements Comparator<Track> {

    public int compare(Track track1, Track track2) {return track2.length - track1.length;}

}