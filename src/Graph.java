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
        cityIndexMap.put(City.AMSTERDAM, 36);
        cityIndexMap.put(City.ANGORA, 37);
        cityIndexMap.put(City.ATHINA, 38);
        cityIndexMap.put(City.BARCELONA, 39);
        cityIndexMap.put(City.BERLIN, 40);
        cityIndexMap.put(City.BREST, 41);
        cityIndexMap.put(City.BRINDISI, 42);
        cityIndexMap.put(City.BRUXELLES, 43);
        cityIndexMap.put(City.BUCURESTI, 44);
        cityIndexMap.put(City.BUDAPEST, 45);
        cityIndexMap.put(City.CADIZ, 46);
        cityIndexMap.put(City.CONSTANTINOPLE, 47);
        cityIndexMap.put(City.DANZIG, 48);
        cityIndexMap.put(City.DIEPPE, 49);
        cityIndexMap.put(City.EDINBURGH, 50);
        cityIndexMap.put(City.ERZURUM, 51);
        cityIndexMap.put(City.ESSEN, 52);
        cityIndexMap.put(City.FRANKFURT, 53);
        cityIndexMap.put(City.KHARKOV, 54);
        cityIndexMap.put(City.KOBENHAVN, 55);
        cityIndexMap.put(City.KYIV, 56);
        cityIndexMap.put(City.LISBOA, 57);
        cityIndexMap.put(City.LONDON, 58);
        cityIndexMap.put(City.MADRID, 59);
        cityIndexMap.put(City.MARCEILLE, 60);
        cityIndexMap.put(City.MOSKVA, 61);
        cityIndexMap.put(City.MUNCHEN, 62);
        cityIndexMap.put(City.PALERMO, 63);
        cityIndexMap.put(City.PAMPLONA, 64);
        cityIndexMap.put(City.PARIS, 65);
        cityIndexMap.put(City.PETROGRAD, 66);
        cityIndexMap.put(City.RIGA, 67);
        cityIndexMap.put(City.ROMA, 68);
        cityIndexMap.put(City.ROSTOV, 69);
        cityIndexMap.put(City.SARAJEVO, 70);
        cityIndexMap.put(City.SEVASTOPOL, 71);
        cityIndexMap.put(City.SMOLENSK, 72);
        cityIndexMap.put(City.SMYRNA, 73);
        cityIndexMap.put(City.SOCHI, 74);
        cityIndexMap.put(City.SOFIA, 75);
        cityIndexMap.put(City.STOCKHOLM, 76);
        cityIndexMap.put(City.VENEZIA, 77);
        cityIndexMap.put(City.WARSAWA, 78);
        cityIndexMap.put(City.WIEN, 79);
        cityIndexMap.put(City.WILNO, 80);
        cityIndexMap.put(City.ZAGRAB, 81);
        cityIndexMap.put(City.ZURICH, 82);
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
        indexCityMap.put(36, City.AMSTERDAM);
        indexCityMap.put(37, City.ANGORA);
        indexCityMap.put(38, City.ATHINA);
        indexCityMap.put(39, City.BARCELONA);
        indexCityMap.put(40, City.BERLIN);
        indexCityMap.put(41, City.BREST);
        indexCityMap.put(42, City.BRINDISI);
        indexCityMap.put(43, City.BRUXELLES);
        indexCityMap.put(44, City.BUCURESTI);
        indexCityMap.put(45, City.BUDAPEST);
        indexCityMap.put(46, City.CADIZ);
        indexCityMap.put(47, City.CONSTANTINOPLE);
        indexCityMap.put(48, City.DANZIG);
        indexCityMap.put(49, City.DIEPPE);
        indexCityMap.put(50, City.EDINBURGH);
        indexCityMap.put(51, City.ERZURUM);
        indexCityMap.put(52, City.ESSEN);
        indexCityMap.put(53, City.FRANKFURT);
        indexCityMap.put(54, City.KHARKOV);
        indexCityMap.put(55, City.KOBENHAVN);
        indexCityMap.put(56, City.KYIV);
        indexCityMap.put(57, City.LISBOA);
        indexCityMap.put(58, City.LONDON);
        indexCityMap.put(59, City.MADRID);
        indexCityMap.put(60, City.MARCEILLE);
        indexCityMap.put(61, City.MOSKVA);
        indexCityMap.put(62, City.MUNCHEN);
        indexCityMap.put(63, City.PALERMO);
        indexCityMap.put(64, City.PAMPLONA);
        indexCityMap.put(65, City.PARIS);
        indexCityMap.put(66, City.PETROGRAD);
        indexCityMap.put(67, City.RIGA);
        indexCityMap.put(68, City.ROMA);
        indexCityMap.put(69, City.ROSTOV);
        indexCityMap.put(70, City.SARAJEVO);
        indexCityMap.put(71, City.SEVASTOPOL);
        indexCityMap.put(72, City.SMOLENSK);
        indexCityMap.put(73, City.SMYRNA);
        indexCityMap.put(74, City.SOCHI);
        indexCityMap.put(75, City.SOFIA);
        indexCityMap.put(76, City.STOCKHOLM);
        indexCityMap.put(77, City.VENEZIA);
        indexCityMap.put(78, City.WARSAWA);
        indexCityMap.put(79, City.WIEN);
        indexCityMap.put(80, City.WILNO);
        indexCityMap.put(81, City.ZAGRAB);
        indexCityMap.put(82, City.ZURICH);
    }

    private void initalizeGraph() {
        for(int i = 0; i < numVertices; i++) {
            graph[i] = new ArrayList<>();
        }
    }
}