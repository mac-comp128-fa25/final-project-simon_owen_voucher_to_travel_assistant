import java.util.Comparator;
import java.util.List;

public class TrackListComparator implements Comparator<List<Track>>{

    public int compare(List<Track> t1, List<Track> t2) {
        int lengthT1 = 0;
        int lengthT2 = 0;

        for(Track track : t1) {
            lengthT1 += track.getLength();
        } 

        for(Track track : t2) {
            lengthT2 += track.getLength();
        }

        return lengthT1 - lengthT2;
    }

}
