import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.ArrayList;

public class Board {

    private Map<City,Integer> cityIndexMap;
    private Graph graph;
    private int numVertices;
    private TrainCard[] shop = new TrainCard[5]; // Add edge cases to reshuffle the shop

    public Board() {
        this.graph = new Graph();
        numVertices = graph.getNumVertices();
        cityIndexMap = new HashMap<>(numVertices);
        addCityIndices();
    }

    public int getCityIndex(City city) {
        return cityIndexMap.get(city);
    }

    private void addCityIndices() {
        cityIndexMap.put(City.ATLANTA, 0);
        cityIndexMap.put(City.BOSTON, 1);
        cityIndexMap.put(City.CALGARY, 2);
        cityIndexMap.put(City.CHICAGO, 3);
        cityIndexMap.put(City.DALLAS, 4);
        cityIndexMap.put(City.DENVER, 5);
        cityIndexMap.put(City.DULUTH, 6);
        cityIndexMap.put(City.EP, 7);
        cityIndexMap.put(City.HELENA, 8);
        cityIndexMap.put(City.HOUSTON, 9);
        cityIndexMap.put(City.KC, 10);
        cityIndexMap.put(City.LV, 11);
        cityIndexMap.put(City.LA, 12);
        cityIndexMap.put(City.LR, 13);
        cityIndexMap.put(City.MIAMI, 14);
        cityIndexMap.put(City.MONTREAL, 15);
        cityIndexMap.put(City.NASHVILLE, 16);
        cityIndexMap.put(City.NO, 17);
        cityIndexMap.put(City.NYC, 18);
        cityIndexMap.put(City.OKC, 19);
        cityIndexMap.put(City.OMAHA, 20);
        cityIndexMap.put(City.PHOENIX, 21);
        cityIndexMap.put(City.PITTSBURGH, 22);
        cityIndexMap.put(City.PORTLAND, 23);
        cityIndexMap.put(City.RALEIGH, 24);
        cityIndexMap.put(City.STL, 25);
        cityIndexMap.put(City.SLC, 26);
        cityIndexMap.put(City.SANFRAN, 27);
        cityIndexMap.put(City.SANTAFE, 28);
        cityIndexMap.put(City.SEATTLE, 29);
        cityIndexMap.put(City.SSM, 30);
        cityIndexMap.put(City.TORONTO, 31);
        cityIndexMap.put(City.VANCOUVER, 32);
        cityIndexMap.put(City.WASHINGTON, 33);
        cityIndexMap.put(City.WINNIPEG, 34);
    }

    private void addTracks() {
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.CALGARY), 3, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SEATTLE), cityIndexMap.get(City.CALGARY), 4, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SEATTLE), cityIndexMap.get(City.PORTLAND), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SEATTLE), cityIndexMap.get(City.PORTLAND), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SEATTLE), cityIndexMap.get(City.HELENA), 6, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.PORTLAND), cityIndexMap.get(City.SLC), 6, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.PORTLAND), cityIndexMap.get(City.SANFRAN), 5, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);
        graph.addTrack(cityIndexMap.get(City.VANCOUVER), cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD, 2);


    }

    public ArrayList<int[]> getShortestPath(Graph graph, City startingCity, City endingCity) {
        //Initialize PQ
        PriorityQueue<ArrayList<int[]>> shortestPathQueue = new PriorityQueue<>(new CityWeightComparator());
        //create starting city-weight pair and add to PQ
        int[] startingCityWeight = new int[] {cityIndexMap.get(startingCity), 0};
        ArrayList<int[]> startingCityPath = new ArrayList<>();
        startingCityPath.add(startingCityWeight);
        shortestPathQueue.add(startingCityPath);
        //Dijkstra's Alg
        while(!shortestPathQueue.isEmpty()) {
            ArrayList<int[]> currentCityPath = shortestPathQueue.remove();
            ArrayList<int[]> outBoundPaths = graph.getTracksOutOfCityIndex(currentCityPath.getLast()[0]);
            for(int[] path : outBoundPaths) {
                ArrayList<int[]> nextCityPath = (ArrayList<int[]>) currentCityPath.clone();
                nextCityPath.add(path);
                shortestPathQueue.add(nextCityPath);
                if(path[0] == cityIndexMap.get(endingCity)) {
                    return nextCityPath;
                }
            }
        }
        return null;
    }

    public RouteCard drawRouteCard(Player player) {
        return null;
    }

    public void discardRouteCard(Player player, RouteCard card) {

    }

    public void spendTrainCard(Player player, TrainCard card) {

    }

    public TrainCard drawTopTrainCard(Player player) {
        return null;
    }

    public TrainCard drawTrainCard(Player player) {
        return null;
    }

    public void replaceTrainRow() {

    }

    public Deque<Card> shuffle(Deque<Card> deck) {
        return null;
    }

    
}