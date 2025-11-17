import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

    /*
     * Find shortest path from start city to end city. 
     * BFS with priority queue and priority based on total track length (minheap)
     * 
     */
     public List<City> getShortestPath(City startingCity, City endingCity) {
        PriorityQueue<List<Track>> shortestTrackQueue = new PriorityQueue<>(new TrackListComparator());
        // get adjacent cities to starting city
        // enqueue all tracks
        // while loop:
        //    dequeue first track
        //    get all adjacent cities from destination of track
        //    enqueue all tracks from each adjacent city, add these tracks to a list containing the 
        //    
        // RESEARCH DIJKSTRA'S ALGORITHM


     }

     

}
