import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

    public ArrayList<Track>[][] graph;
    private int numVertices = City.values().length;
    
    public Graph() {
        graph = (ArrayList<Track>[][]) new ArrayList[numVertices][numVertices];
    }

    public void addTrack(int i, int j, int length, TrainColor color) {
        graph[i][j].add(new Track(length, color));
        graph[j][i].add(new Track(length, color));
    }

    public void removeTrack(int i, int j, Track track) {
        if(graph[i][j].contains(track)) {
            graph[i][j].remove(track);
            graph[j][i].remove(track);
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    public List<int[]> getTracksOutOfCityIndex(int index) {
        List<int[]> cityWeight = new ArrayList<>();
        for(int i = 0; i < City.values().length; i++) {
            if(graph[index][i].size() != 0) {
                int[] cityWeightPair = new int[]{i,graph[index][i].getFirst().getLength()};
                cityWeight.add(cityWeightPair);
            }
        }
        return cityWeight;
    }
}