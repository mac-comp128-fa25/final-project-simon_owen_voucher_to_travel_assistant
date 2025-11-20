import java.util.Comparator;

public class CityWeightComparator implements Comparator<int[]> {

    public int compare(int[] p1, int[] p2) {

        return p1[1] - p2[1];

    }

}   
