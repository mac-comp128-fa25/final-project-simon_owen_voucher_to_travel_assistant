public class Graph {

    private Track[][] graph;
    private int numVertices = City.values().length;
    
    public Graph() {
        graph = new Track[numVertices][numVertices];
    }

    public void addTrack(int i, int j, int length, TrainColor color) {
        graph[i][j] = new Track(length, color);
        graph[j][i] = new Track(length, color);
    }

    public void addTrack(int i, int j, int length, TrainColor color, int tracks) {
        graph[i][j] = new Track(length, color, tracks);
        graph[j][i] = new Track(length, color, tracks);
    }

    public void removeTrack(int i, int j) {
        graph[i][j] = null;
    }

    public int getNumVertices() {
        return numVertices;
    }

}
