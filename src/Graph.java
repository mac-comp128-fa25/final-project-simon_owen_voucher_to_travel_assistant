import java.util.ArrayList;

public class Graph {

    private ArrayList<Track>[][] graph;
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

    

}
