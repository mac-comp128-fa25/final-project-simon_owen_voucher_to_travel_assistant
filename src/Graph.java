import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    public ArrayList<Track>[] graph;
    private int numVertices = City.values().length;
    public Map<City,Integer> cityIndexMap;
    public Map<Integer,City> indexCityMap;
    

    /**
     * Object used to represent the ticket to ride board
     */
    public Graph() {
        graph = (ArrayList<Track>[]) new ArrayList[numVertices];
        cityIndexMap = new HashMap<>(numVertices);
        indexCityMap = new HashMap<>(numVertices);
        addCityIndices();
        initalizeGraph();
    }


    /**
     * Adds a track to the graph
     * @param i the start vertex of the track
     * @param j the end vertex of the track
     * @param length the length of the track (the weight of the edge)
     * @param color the color of the track
     */
    public void addTrack(int i, int j, int length, TrainColor color) {
        graph[i].add(new Track(indexCityMap.get(i), indexCityMap.get(j), length, color));
        graph[j].add(new Track(indexCityMap.get(j), indexCityMap.get(i), length, color));
    }


    /**
     * Removes a track from the graph
     * @param track the track to be removed
     */
    public void removeTrack(Track track) {
        for(Track track1 : graph[cityIndexMap.get(track.startCity)]) {
            if(track1.endCity == track.endCity && track1.color == track.color) {
                graph[cityIndexMap.get(track.startCity)].remove(track1);
                break;
            }
        }
        for(Track track2 : graph[cityIndexMap.get(track.endCity)]) {
            if(track2.endCity == track.startCity && track2.color == track.color) {
                graph[cityIndexMap.get(track.startCity)].remove(track2);
                break;
            }
        }
    }


    /**
     * @param i the index of the start city
     * @param j the index of the end city
     * @return List of tracks out of the city at index i and into the city at index j
     */
    public List<Track> getTracks(int i, int j) {
        List<Track> list = new ArrayList<>();
        for(Track track : graph[i]) {
            if(track.endCity == indexCityMap.get(j)) {
                list.add(track);
            }
        }
        return list;
    }


    /**
     * @return The number of vertices in the graph, this number is 36 unless we introduce new cities
     */
    public int getNumVertices() {
        return numVertices;
    }


    /**
     * @return A list of all tracks still in the graph (i.e. tracks that have not been purchased yet)
     */
    public List<Track> getAllTracks() {
        List<Track> allTracks = new ArrayList<>();
        for(ArrayList<Track> tracks : graph) {
            for(Track track : tracks) {
                if(!allTracks.contains(track)) {
                    allTracks.add(track);
                }
            }
        }
        return allTracks;
    }


    /**
     * Gets all cityweight pairs out of a specific city
     * @param index the index of the city
     * @return A list of length 2 arrays where the first index corresponds to the index of the adjacent city and the second index
     * corresponds to the distance between the two cities.
     */
    public List<int[]> getPathsOutOfCityIndex(int index) {
        List<int[]> cityWeight = new ArrayList<>();
        for(Track track : graph[index]) {
            int[] cityWeightPair = new int[]{cityIndexMap.get(track.endCity), track.length};
            cityWeight.add(cityWeightPair);
        }
        return cityWeight;
    }


    /**
     * Returns a track between two cities
     * @param city1 The start city
     * @param city2 The end city
     * @return The first occurence of a track that exists between the two cities, or null if none exist
     */
    public Track getTrackFromCities(Integer city1, Integer city2) {
        for(Track track : graph[city1]) {
            if(track.endCity == indexCityMap.get(city2)) {
                return track;
            }
        }
        return null;
    }


    /**
     * Sets each city to an index
     */
    private void addCityIndices() {
        cityIndexMap.put(City.ATLANTA, 0);
        cityIndexMap.put(City.BOSTON, 1);
        cityIndexMap.put(City.CALGARY, 2);
        cityIndexMap.put(City.CHARLESTON, 3);
        cityIndexMap.put(City.CHICAGO, 4);
        cityIndexMap.put(City.DALLAS, 5);
        cityIndexMap.put(City.DENVER, 6);
        cityIndexMap.put(City.DULUTH, 7);
        cityIndexMap.put(City.EP, 8);
        cityIndexMap.put(City.HELENA, 9);
        cityIndexMap.put(City.HOUSTON, 10);
        cityIndexMap.put(City.KC, 11);
        cityIndexMap.put(City.LV, 12);
        cityIndexMap.put(City.LA, 13);
        cityIndexMap.put(City.LR, 14);
        cityIndexMap.put(City.MIAMI, 15);
        cityIndexMap.put(City.MONTREAL, 16);
        cityIndexMap.put(City.NASHVILLE, 17);
        cityIndexMap.put(City.NO, 18);
        cityIndexMap.put(City.NYC, 19);
        cityIndexMap.put(City.OKC, 20);
        cityIndexMap.put(City.OMAHA, 21);
        cityIndexMap.put(City.PHOENIX, 22);
        cityIndexMap.put(City.PITTSBURGH, 23);
        cityIndexMap.put(City.PORTLAND, 24);
        cityIndexMap.put(City.RALEIGH, 25);
        cityIndexMap.put(City.STL, 26);
        cityIndexMap.put(City.SLC, 27);
        cityIndexMap.put(City.SANFRAN, 28);
        cityIndexMap.put(City.SANTAFE, 29);
        cityIndexMap.put(City.SEATTLE, 30);
        cityIndexMap.put(City.SSM, 31);
        cityIndexMap.put(City.TORONTO, 32);
        cityIndexMap.put(City.VANCOUVER, 33);
        cityIndexMap.put(City.WASHINGTON, 34);
        cityIndexMap.put(City.WINNIPEG, 35);
        indexCityMap.put(0, City.ATLANTA);
        indexCityMap.put(1, City.BOSTON);
        indexCityMap.put(2, City.CALGARY);
        indexCityMap.put(3, City.CHARLESTON);
        indexCityMap.put(4, City.CHICAGO);
        indexCityMap.put(5, City.DALLAS);
        indexCityMap.put(6, City.DENVER);
        indexCityMap.put(7, City.DULUTH);
        indexCityMap.put(8, City.EP);
        indexCityMap.put(9, City.HELENA);
        indexCityMap.put(10, City.HOUSTON);
        indexCityMap.put(11, City.KC);
        indexCityMap.put(12, City.LV);
        indexCityMap.put(13, City.LA);
        indexCityMap.put(14, City.LR);
        indexCityMap.put(15, City.MIAMI);
        indexCityMap.put(16, City.MONTREAL);
        indexCityMap.put(17, City.NASHVILLE);
        indexCityMap.put(18, City.NO);
        indexCityMap.put(19, City.NYC);
        indexCityMap.put(20, City.OKC);
        indexCityMap.put(21, City.OMAHA);
        indexCityMap.put(22, City.PHOENIX);
        indexCityMap.put(23, City.PITTSBURGH);
        indexCityMap.put(24, City.PORTLAND);
        indexCityMap.put(25, City.RALEIGH);
        indexCityMap.put(26, City.STL);
        indexCityMap.put(27, City.SLC);
        indexCityMap.put(28, City.SANFRAN);
        indexCityMap.put(29, City.SANTAFE);
        indexCityMap.put(30, City.SEATTLE);
        indexCityMap.put(31, City.SSM);
        indexCityMap.put(32, City.TORONTO);
        indexCityMap.put(33, City.VANCOUVER);
        indexCityMap.put(34, City.WASHINGTON);
        indexCityMap.put(35, City.WINNIPEG);
    }

    private void initalizeGraph() {
        for(int i = 0; i < numVertices; i++) {
            graph[i] = new ArrayList<>();
        }
    }
}