import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<City,Integer> cityIndexMap;
    private Graph graph;
    private int numVertices;

    public Board() {
        this.graph = new Graph();
        numVertices = graph.getNumVertices();
        cityIndexMap = new HashMap<>(numVertices);
        addCityIndices();
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

    
}