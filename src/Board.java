import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Board {

    private Map<City,Integer> cityIndexMap;
    private Map<Integer,City> indexCityMap;
    private Graph graph;
    private int numVertices;
    private TrainCard[] shop = new TrainCard[5]; // Add edge cases to reshuffle the shop

    public Board() {
        this.graph = new Graph();
        numVertices = graph.getNumVertices();
        cityIndexMap = new HashMap<>(numVertices);
        indexCityMap = new HashMap<>(numVertices);
        addCityIndices();
        addTracks();
    }

    public int getIndexFromCity(City city) {
        return cityIndexMap.get(city);
    }

    public City getCityFromIndex(int index) {
        return indexCityMap.get(index);
    }

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
        graph.addTrack(cityIndexMap.get(City.SANFRAN), cityIndexMap.get(City.SLC), 5, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.SANFRAN), cityIndexMap.get(City.SLC), 5, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.SLC), cityIndexMap.get(City.HELENA), 3, TrainColor.PINK);
        graph.addTrack(cityIndexMap.get(City.CALGARY), cityIndexMap.get(City.HELENA), 4, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SANFRAN), cityIndexMap.get(City.LA), 3, TrainColor.PINK);
        graph.addTrack(cityIndexMap.get(City.SANFRAN), cityIndexMap.get(City.LA), 3, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.LA), cityIndexMap.get(City.LV), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.LA), cityIndexMap.get(City.PHOENIX), 3, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.LA), cityIndexMap.get(City.EP), 6, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.LV), cityIndexMap.get(City.SLC), 3, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.PHOENIX), cityIndexMap.get(City.DENVER), 5, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.PHOENIX), cityIndexMap.get(City.SANTAFE), 3, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.PHOENIX), cityIndexMap.get(City.EP), 3, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SANTAFE), cityIndexMap.get(City.EP), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SANTAFE), cityIndexMap.get(City.DENVER), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SANTAFE), cityIndexMap.get(City.OKC), 3, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.EP), cityIndexMap.get(City.OKC), 5, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.EP), cityIndexMap.get(City.DALLAS), 4, TrainColor.RED);
        graph.addTrack(cityIndexMap.get(City.EP), cityIndexMap.get(City.HOUSTON), 6, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.SLC), cityIndexMap.get(City.DENVER), 3, TrainColor.RED);
        graph.addTrack(cityIndexMap.get(City.SLC), cityIndexMap.get(City.DENVER), 3, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.CALGARY), cityIndexMap.get(City.WINNIPEG), 6, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.HELENA), cityIndexMap.get(City.WINNIPEG), 4, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.HELENA), cityIndexMap.get(City.DULUTH), 6, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.HELENA), cityIndexMap.get(City.OMAHA), 5, TrainColor.RED);
        graph.addTrack(cityIndexMap.get(City.HELENA), cityIndexMap.get(City.DENVER), 4, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.DENVER), cityIndexMap.get(City.OMAHA), 4, TrainColor.PINK);
        graph.addTrack(cityIndexMap.get(City.DENVER), cityIndexMap.get(City.KC), 4, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.DENVER), cityIndexMap.get(City.KC), 4, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.DENVER), cityIndexMap.get(City.OKC), 4, TrainColor.RED);
        graph.addTrack(cityIndexMap.get(City.WINNIPEG), cityIndexMap.get(City.DULUTH), 4, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.WINNIPEG), cityIndexMap.get(City.SSM), 6, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DULUTH), cityIndexMap.get(City.SSM), 3, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DULUTH), cityIndexMap.get(City.OMAHA), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DULUTH), cityIndexMap.get(City.OMAHA), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DULUTH), cityIndexMap.get(City.TORONTO), 6, TrainColor.PINK);
        graph.addTrack(cityIndexMap.get(City.DULUTH), cityIndexMap.get(City.CHICAGO), 3, TrainColor.RED);
        graph.addTrack(cityIndexMap.get(City.OMAHA), cityIndexMap.get(City.CHICAGO), 4, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.OMAHA), cityIndexMap.get(City.KC), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.OMAHA), cityIndexMap.get(City.KC), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.KC), cityIndexMap.get(City.OKC), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.KC), cityIndexMap.get(City.OKC), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.KC), cityIndexMap.get(City.STL), 2, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.KC), cityIndexMap.get(City.STL), 2, TrainColor.PINK);
        graph.addTrack(cityIndexMap.get(City.OKC), cityIndexMap.get(City.DALLAS), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.OKC), cityIndexMap.get(City.DALLAS), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DALLAS), cityIndexMap.get(City.HOUSTON), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DALLAS), cityIndexMap.get(City.HOUSTON), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.DALLAS), cityIndexMap.get(City.LR), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.OKC), cityIndexMap.get(City.LR), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.HOUSTON), cityIndexMap.get(City.NO), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.LR), cityIndexMap.get(City.STL), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.LR), cityIndexMap.get(City.NO), 3, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.LR), cityIndexMap.get(City.NASHVILLE), 3, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.NO), cityIndexMap.get(City.ATLANTA), 4, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.NO), cityIndexMap.get(City.ATLANTA), 4, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.NO), cityIndexMap.get(City.MIAMI), 6, TrainColor.RED);
        graph.addTrack(cityIndexMap.get(City.STL), cityIndexMap.get(City.CHICAGO), 2, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.STL), cityIndexMap.get(City.CHICAGO), 2, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.STL), cityIndexMap.get(City.PITTSBURGH), 5, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.STL), cityIndexMap.get(City.NASHVILLE), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.CHICAGO), cityIndexMap.get(City.TORONTO), 4, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.CHICAGO), cityIndexMap.get(City.PITTSBURGH), 3, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.CHICAGO), cityIndexMap.get(City.PITTSBURGH), 3, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.NASHVILLE), cityIndexMap.get(City.PITTSBURGH), 4, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.NASHVILLE), cityIndexMap.get(City.RALEIGH), 3, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.NASHVILLE), cityIndexMap.get(City.ATLANTA), 1, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.ATLANTA), cityIndexMap.get(City.RALEIGH), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.ATLANTA), cityIndexMap.get(City.RALEIGH), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.ATLANTA), cityIndexMap.get(City.CHARLESTON), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.ATLANTA), cityIndexMap.get(City.MIAMI), 5, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.MIAMI), cityIndexMap.get(City.CHARLESTON), 4, TrainColor.PINK);
        graph.addTrack(cityIndexMap.get(City.CHARLESTON), cityIndexMap.get(City.RALEIGH), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.RALEIGH), cityIndexMap.get(City.PITTSBURGH), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.RALEIGH), cityIndexMap.get(City.WASHINGTON), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.RALEIGH), cityIndexMap.get(City.WASHINGTON), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.WASHINGTON), cityIndexMap.get(City.PITTSBURGH), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.WASHINGTON), cityIndexMap.get(City.NYC), 2, TrainColor.ORANGE);
        graph.addTrack(cityIndexMap.get(City.WASHINGTON), cityIndexMap.get(City.NYC), 2, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.SSM), cityIndexMap.get(City.TORONTO), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.SSM), cityIndexMap.get(City.MONTREAL), 5, TrainColor.BLACK);
        graph.addTrack(cityIndexMap.get(City.MONTREAL), cityIndexMap.get(City.TORONTO), 3, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.TORONTO), cityIndexMap.get(City.PITTSBURGH), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.PITTSBURGH), cityIndexMap.get(City.NYC), 2, TrainColor.WHITE);
        graph.addTrack(cityIndexMap.get(City.PITTSBURGH), cityIndexMap.get(City.NYC), 2, TrainColor.GREEN);
        graph.addTrack(cityIndexMap.get(City.MONTREAL), cityIndexMap.get(City.NYC), 3, TrainColor.BLUE);
        graph.addTrack(cityIndexMap.get(City.MONTREAL), cityIndexMap.get(City.BOSTON), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.MONTREAL), cityIndexMap.get(City.BOSTON), 2, TrainColor.WILD);
        graph.addTrack(cityIndexMap.get(City.BOSTON), cityIndexMap.get(City.NYC), 2, TrainColor.YELLOW);
        graph.addTrack(cityIndexMap.get(City.BOSTON), cityIndexMap.get(City.NYC), 2, TrainColor.RED);
    }


    public List<Integer> dijkstraSearch(City startCity, City endCity) {
        // Declare Variables:
        int startCityIndex = cityIndexMap.get(startCity);
        int endCityIndex = cityIndexMap.get(endCity);
        int[] currentCityWeightPair;
        
        // Initalize Hashmaps:
        HashMap<Integer, Integer> cityIndexToDistanceMap = new HashMap<>();
        HashMap<Integer, Integer> cityIndexToPreviousCity = new HashMap<>();
        
        // Intitalize Priority Queue:
        PriorityQueue<int[]> shortestPathQueue = new PriorityQueue<>(new CityWeightComparator());
        int[] startCityWeight = new int[]{startCityIndex, 0};
        cityIndexToDistanceMap.put(startCityIndex, 0);
        cityIndexToPreviousCity.put(startCityIndex, null);
        shortestPathQueue.add(startCityWeight);

        // While Loop
        while(!shortestPathQueue.isEmpty()) {

            currentCityWeightPair = shortestPathQueue.remove();

            List<int[]> currentCityWeightPairNeighbors = graph.getTracksOutOfCityIndex(currentCityWeightPair[0]);

            for(int[] neighborCityWeightPair : currentCityWeightPairNeighbors) {
                if(!cityIndexToPreviousCity.containsKey(neighborCityWeightPair[0])) {
                    cityIndexToDistanceMap.put(neighborCityWeightPair[0], cityIndexToDistanceMap.get(currentCityWeightPair[0])+neighborCityWeightPair[1]);
                    cityIndexToPreviousCity.put(neighborCityWeightPair[0], currentCityWeightPair[0]);
                    int[] updatedNeighborCityWeightPair = new int[]{neighborCityWeightPair[0], cityIndexToDistanceMap.get(neighborCityWeightPair[0])};
                    shortestPathQueue.add(updatedNeighborCityWeightPair);
                } else if(cityIndexToDistanceMap.get(neighborCityWeightPair[0]) > cityIndexToDistanceMap.get(currentCityWeightPair[0])+neighborCityWeightPair[1]) {
                    cityIndexToDistanceMap.put(neighborCityWeightPair[0], cityIndexToDistanceMap.get(currentCityWeightPair[0])+neighborCityWeightPair[1]);
                    cityIndexToPreviousCity.put(neighborCityWeightPair[0], currentCityWeightPair[0]);
                }
            }
        }

        Deque<Integer> finalCityStack = new ArrayDeque<>();
        Integer currentCityIndex = endCityIndex;
        while(currentCityIndex != null) {
            finalCityStack.push(currentCityIndex);
            currentCityIndex = cityIndexToPreviousCity.get(currentCityIndex);
        }

        List<Integer> finalPath = new ArrayList<>();
        while(!finalCityStack.isEmpty()) {
            finalPath.add(finalCityStack.pop());
        }

        return finalPath;

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