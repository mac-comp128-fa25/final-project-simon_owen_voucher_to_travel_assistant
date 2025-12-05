import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Board {

    private Graph graph;
    private TrainCard[] shop = new TrainCard[5]; // Add edge cases to reshuffle the shop
    private Deque<Card> trainCardDeck;
    private List<Card> trainCardDiscard;
    private Deque<Card> routeCardDeck;
    private List<Card> routeCardDiscard;

    public Board() {
        this.graph = new Graph();
        addTracks();
        setup();
    }

    private void setup() {
        // Initialize the Decks and Shop
        initializeTrainCardDeck();
        initializeRouteCardDeck();
    }

    private void initializeTrainCardDeck() {
        trainCardDiscard = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.RED));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.ORANGE));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.YELLOW));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.GREEN));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.BLUE));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.PINK));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.WHITE));
        }
        for(int i = 0; i < 12; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.BLACK));
        }
        for(int i = 0; i < 14; i++) {
            trainCardDiscard.add(new TrainCard(TrainColor.WILD));
        }

        trainCardDeck = shuffle(trainCardDiscard);
    }

    private void initializeRouteCardDeck() {
        routeCardDiscard = new ArrayList<>();
        routeCardDiscard.add(new RouteCard(City.BOSTON, City.MIAMI, 12));
        routeCardDiscard.add(new RouteCard(City.CALGARY, City.PHOENIX, 13));
        routeCardDiscard.add(new RouteCard(City.CALGARY, City.SLC, 7));
        routeCardDiscard.add(new RouteCard(City.CHICAGO, City.NO, 7));
        routeCardDiscard.add(new RouteCard(City.CHICAGO, City.SANTAFE, 9));
        routeCardDiscard.add(new RouteCard(City.DALLAS, City.NYC, 11));
        routeCardDiscard.add(new RouteCard(City.DENVER, City.EP, 4));
        routeCardDiscard.add(new RouteCard(City.DENVER, City.PITTSBURGH, 11));
        routeCardDiscard.add(new RouteCard(City.DULUTH, City.EP, 10));
        routeCardDiscard.add(new RouteCard(City.DULUTH, City.HOUSTON, 8));
        routeCardDiscard.add(new RouteCard(City.HELENA, City.LA, 8));
        routeCardDiscard.add(new RouteCard(City.KC, City.HOUSTON, 5));
        routeCardDiscard.add(new RouteCard(City.LA, City.CHICAGO, 16));
        routeCardDiscard.add(new RouteCard(City.LA, City.MIAMI, 20));
        routeCardDiscard.add(new RouteCard(City.LA, City.NYC, 21));
        routeCardDiscard.add(new RouteCard(City.MONTREAL, City.ATLANTA, 9));
        routeCardDiscard.add(new RouteCard(City.MONTREAL, City.NO, 13));
        routeCardDiscard.add(new RouteCard(City.NYC, City.ATLANTA, 6));
        routeCardDiscard.add(new RouteCard(City.PORTLAND, City.NASHVILLE, 17));
        routeCardDiscard.add(new RouteCard(City.PORTLAND, City.PHOENIX, 11));
        routeCardDiscard.add(new RouteCard(City.SANFRAN, City.ATLANTA, 17));
        routeCardDiscard.add(new RouteCard(City.SSM, City.NASHVILLE, 8));
        routeCardDiscard.add(new RouteCard(City.SSM, City.OKC, 9));
        routeCardDiscard.add(new RouteCard(City.SEATTLE, City.LA, 9));
        routeCardDiscard.add(new RouteCard(City.SEATTLE, City.NYC, 22));
        routeCardDiscard.add(new RouteCard(City.TORONTO, City.MIAMI, 10));
        routeCardDiscard.add(new RouteCard(City.VANCOUVER, City.MONTREAL, 20));
        routeCardDiscard.add(new RouteCard(City.VANCOUVER, City.SANTAFE, 13));
        routeCardDiscard.add(new RouteCard(City.WINNIPEG, City.HOUSTON, 12));
        routeCardDiscard.add(new RouteCard(City.WINNIPEG, City.LR, 11));

        routeCardDeck = shuffle(routeCardDiscard);
    }

    public void initializeShop() {
        for(int i = 0; i < shop.length; i++) {
            shop[i] = (TrainCard) trainCardDeck.pop();
        }
    }

    public int getIndexFromCity(City city) {
        return graph.cityIndexMap.get(city);
    }

    public City getCityFromIndex(int index) {
        return graph.indexCityMap.get(index);
    }

    public Track getTrackFromCities(Integer city1, Integer city2) {
        return graph.graph[city1][city2].getFirst();

    }

    private void addTracks() {
        graph.addTrack(graph.cityIndexMap.get(City.VANCOUVER), graph.cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.VANCOUVER), graph.cityIndexMap.get(City.SEATTLE), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.VANCOUVER), graph.cityIndexMap.get(City.CALGARY), 3, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SEATTLE), graph.cityIndexMap.get(City.CALGARY), 4, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SEATTLE), graph.cityIndexMap.get(City.PORTLAND), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SEATTLE), graph.cityIndexMap.get(City.PORTLAND), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SEATTLE), graph.cityIndexMap.get(City.HELENA), 6, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.PORTLAND), graph.cityIndexMap.get(City.SLC), 6, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.PORTLAND), graph.cityIndexMap.get(City.SANFRAN), 5, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.SANFRAN), graph.cityIndexMap.get(City.SLC), 5, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.SANFRAN), graph.cityIndexMap.get(City.SLC), 5, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.SLC), graph.cityIndexMap.get(City.HELENA), 3, TrainColor.PINK);
        graph.addTrack(graph.cityIndexMap.get(City.CALGARY), graph.cityIndexMap.get(City.HELENA), 4, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SANFRAN), graph.cityIndexMap.get(City.LA), 3, TrainColor.PINK);
        graph.addTrack(graph.cityIndexMap.get(City.SANFRAN), graph.cityIndexMap.get(City.LA), 3, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.LA), graph.cityIndexMap.get(City.LV), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.LA), graph.cityIndexMap.get(City.PHOENIX), 3, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.LA), graph.cityIndexMap.get(City.EP), 6, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.LV), graph.cityIndexMap.get(City.SLC), 3, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.PHOENIX), graph.cityIndexMap.get(City.DENVER), 5, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.PHOENIX), graph.cityIndexMap.get(City.SANTAFE), 3, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.PHOENIX), graph.cityIndexMap.get(City.EP), 3, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SANTAFE), graph.cityIndexMap.get(City.EP), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SANTAFE), graph.cityIndexMap.get(City.DENVER), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SANTAFE), graph.cityIndexMap.get(City.OKC), 3, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.EP), graph.cityIndexMap.get(City.OKC), 5, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.EP), graph.cityIndexMap.get(City.DALLAS), 4, TrainColor.RED);
        graph.addTrack(graph.cityIndexMap.get(City.EP), graph.cityIndexMap.get(City.HOUSTON), 6, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.SLC), graph.cityIndexMap.get(City.DENVER), 3, TrainColor.RED);
        graph.addTrack(graph.cityIndexMap.get(City.SLC), graph.cityIndexMap.get(City.DENVER), 3, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.CALGARY), graph.cityIndexMap.get(City.WINNIPEG), 6, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.HELENA), graph.cityIndexMap.get(City.WINNIPEG), 4, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.HELENA), graph.cityIndexMap.get(City.DULUTH), 6, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.HELENA), graph.cityIndexMap.get(City.OMAHA), 5, TrainColor.RED);
        graph.addTrack(graph.cityIndexMap.get(City.HELENA), graph.cityIndexMap.get(City.DENVER), 4, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.DENVER), graph.cityIndexMap.get(City.OMAHA), 4, TrainColor.PINK);
        graph.addTrack(graph.cityIndexMap.get(City.DENVER), graph.cityIndexMap.get(City.KC), 4, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.DENVER), graph.cityIndexMap.get(City.KC), 4, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.DENVER), graph.cityIndexMap.get(City.OKC), 4, TrainColor.RED);
        graph.addTrack(graph.cityIndexMap.get(City.WINNIPEG), graph.cityIndexMap.get(City.DULUTH), 4, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.WINNIPEG), graph.cityIndexMap.get(City.SSM), 6, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DULUTH), graph.cityIndexMap.get(City.SSM), 3, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DULUTH), graph.cityIndexMap.get(City.OMAHA), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DULUTH), graph.cityIndexMap.get(City.OMAHA), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DULUTH), graph.cityIndexMap.get(City.TORONTO), 6, TrainColor.PINK);
        graph.addTrack(graph.cityIndexMap.get(City.DULUTH), graph.cityIndexMap.get(City.CHICAGO), 3, TrainColor.RED);
        graph.addTrack(graph.cityIndexMap.get(City.OMAHA), graph.cityIndexMap.get(City.CHICAGO), 4, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.OMAHA), graph.cityIndexMap.get(City.KC), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.OMAHA), graph.cityIndexMap.get(City.KC), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.KC), graph.cityIndexMap.get(City.OKC), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.KC), graph.cityIndexMap.get(City.OKC), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.KC), graph.cityIndexMap.get(City.STL), 2, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.KC), graph.cityIndexMap.get(City.STL), 2, TrainColor.PINK);
        graph.addTrack(graph.cityIndexMap.get(City.OKC), graph.cityIndexMap.get(City.DALLAS), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.OKC), graph.cityIndexMap.get(City.DALLAS), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DALLAS), graph.cityIndexMap.get(City.HOUSTON), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DALLAS), graph.cityIndexMap.get(City.HOUSTON), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.DALLAS), graph.cityIndexMap.get(City.LR), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.OKC), graph.cityIndexMap.get(City.LR), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.HOUSTON), graph.cityIndexMap.get(City.NO), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.LR), graph.cityIndexMap.get(City.STL), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.LR), graph.cityIndexMap.get(City.NO), 3, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.LR), graph.cityIndexMap.get(City.NASHVILLE), 3, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.NO), graph.cityIndexMap.get(City.ATLANTA), 4, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.NO), graph.cityIndexMap.get(City.ATLANTA), 4, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.NO), graph.cityIndexMap.get(City.MIAMI), 6, TrainColor.RED);
        graph.addTrack(graph.cityIndexMap.get(City.STL), graph.cityIndexMap.get(City.CHICAGO), 2, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.STL), graph.cityIndexMap.get(City.CHICAGO), 2, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.STL), graph.cityIndexMap.get(City.PITTSBURGH), 5, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.STL), graph.cityIndexMap.get(City.NASHVILLE), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.CHICAGO), graph.cityIndexMap.get(City.TORONTO), 4, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.CHICAGO), graph.cityIndexMap.get(City.PITTSBURGH), 3, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.CHICAGO), graph.cityIndexMap.get(City.PITTSBURGH), 3, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.NASHVILLE), graph.cityIndexMap.get(City.PITTSBURGH), 4, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.NASHVILLE), graph.cityIndexMap.get(City.RALEIGH), 3, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.NASHVILLE), graph.cityIndexMap.get(City.ATLANTA), 1, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.ATLANTA), graph.cityIndexMap.get(City.RALEIGH), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.ATLANTA), graph.cityIndexMap.get(City.RALEIGH), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.ATLANTA), graph.cityIndexMap.get(City.CHARLESTON), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.ATLANTA), graph.cityIndexMap.get(City.MIAMI), 5, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.MIAMI), graph.cityIndexMap.get(City.CHARLESTON), 4, TrainColor.PINK);
        graph.addTrack(graph.cityIndexMap.get(City.CHARLESTON), graph.cityIndexMap.get(City.RALEIGH), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.RALEIGH), graph.cityIndexMap.get(City.PITTSBURGH), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.RALEIGH), graph.cityIndexMap.get(City.WASHINGTON), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.RALEIGH), graph.cityIndexMap.get(City.WASHINGTON), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.WASHINGTON), graph.cityIndexMap.get(City.PITTSBURGH), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.WASHINGTON), graph.cityIndexMap.get(City.NYC), 2, TrainColor.ORANGE);
        graph.addTrack(graph.cityIndexMap.get(City.WASHINGTON), graph.cityIndexMap.get(City.NYC), 2, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.SSM), graph.cityIndexMap.get(City.TORONTO), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.SSM), graph.cityIndexMap.get(City.MONTREAL), 5, TrainColor.BLACK);
        graph.addTrack(graph.cityIndexMap.get(City.MONTREAL), graph.cityIndexMap.get(City.TORONTO), 3, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.TORONTO), graph.cityIndexMap.get(City.PITTSBURGH), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.PITTSBURGH), graph.cityIndexMap.get(City.NYC), 2, TrainColor.WHITE);
        graph.addTrack(graph.cityIndexMap.get(City.PITTSBURGH), graph.cityIndexMap.get(City.NYC), 2, TrainColor.GREEN);
        graph.addTrack(graph.cityIndexMap.get(City.MONTREAL), graph.cityIndexMap.get(City.NYC), 3, TrainColor.BLUE);
        graph.addTrack(graph.cityIndexMap.get(City.MONTREAL), graph.cityIndexMap.get(City.BOSTON), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.MONTREAL), graph.cityIndexMap.get(City.BOSTON), 2, TrainColor.WILD);
        graph.addTrack(graph.cityIndexMap.get(City.BOSTON), graph.cityIndexMap.get(City.NYC), 2, TrainColor.YELLOW);
        graph.addTrack(graph.cityIndexMap.get(City.BOSTON), graph.cityIndexMap.get(City.NYC), 2, TrainColor.RED);
    }

    private void checkShop() {
        int colorCount = 0;
        int wildCount = 0;
        TrainColor firstColor = shop[0].color;
        for(TrainCard card : shop) {
            if(card.color == TrainColor.WILD) {
                wildCount++;
            } else if(card.color == firstColor) {
                colorCount++;
            }
        }
        if(colorCount == 5 || wildCount >=3) {
            replaceShop();
        }
    }

    public TrainCard[] viewShop() {
        return shop;
    }

    private void replaceShop() {
        for(int i = 0; i < shop.length; i++) {
            trainCardDiscard.add(shop[i]);
            shop[i] = (TrainCard) trainCardDeck.pop();
            if(trainCardDeck.isEmpty()) {
                trainCardDeck = shuffle(trainCardDiscard);
                trainCardDiscard = new ArrayList<>();
            }
        }
    }

    public boolean drawTrainCardFromShop(Player player, int cardIndexInShop) {
        TrainCard card = shop[cardIndexInShop];
        replaceCardInShop(cardIndexInShop);
        checkShop();
        if(card != null) {
            player.drawTrainCard(card);
            return true;
        }
        return false;
    }

    private void replaceCardInShop(int cardIndexInShop) {
        shop[cardIndexInShop] = (TrainCard) trainCardDeck.pop();
        if(trainCardDeck.isEmpty()) {
            trainCardDeck = shuffle(trainCardDiscard);
            trainCardDiscard = new ArrayList<>();
        }
    }

    public int findColorInShop(TrainColor color) {
        for(int i = 0; i < shop.length; i++) {
            if(shop[i].color == color) {
                return i;
            }
        }
        return -1;
    }

    public void drawTopTrainCard(Player player) {
        TrainCard card = (TrainCard) trainCardDeck.pop();
        player.drawTrainCard(card);
        if(trainCardDeck.isEmpty()) {
            trainCardDeck = shuffle(trainCardDiscard);
            trainCardDiscard = new ArrayList<>();
        }
    }

    public void removeTrack(int i, int j) {
        graph.removeTrack(i, j);
    }

    public void removeTrack(int i, int j, Track track) {
        graph.removeTrack(i, j, track);
    }

    public List<Integer> dijkstraSearch(City startCity, City endCity) {
        // Declare Variables:
        int startCityIndex = graph.cityIndexMap.get(startCity);
        int endCityIndex = graph.cityIndexMap.get(endCity);
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

            List<int[]> currentCityWeightPairNeighbors = graph.getPathsOutOfCityIndex(currentCityWeightPair[0]);

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
        
        //TODO: Catch error if city is unreacheable, a.k.a it appears alone in the stack

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

    public void drawRouteCard(Player player) {
        player.drawRouteCard((RouteCard) routeCardDeck.pop());
        if(routeCardDeck.isEmpty()) {
            routeCardDeck = shuffle(routeCardDiscard);
            routeCardDiscard = new ArrayList<>();
        }
    }

    public void discardRouteCard(Player player, RouteCard card) {
        
    }

    public boolean buildTrain(Player player, City startCity, City endCity, TrainColor color) {
        List<Track> validTracks = graph.getTracks(graph.cityIndexMap.get(startCity), graph.cityIndexMap.get(endCity));
        for(Track track : validTracks) {
            if(track.color == color || track.color == TrainColor.WILD) {
                if(playTrainCards(player, color, track.length)){
                    player.buyTrack(startCity, endCity, track.length, color);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean playTrainCards(Player player, TrainColor color, int quantity) {
        if(player.spendTrainCards(color, quantity)){
            for(int i = 0; i < quantity; i++) {
                trainCardDiscard.add(new TrainCard(color));
            }
            return true;
        } else {
            return false;
        }
    }

    public RouteCard[] viewThreeRoutes() {
        RouteCard[] threeRoutes = new RouteCard[3];
        for(int i = 0; i < 3; i++) {
            threeRoutes[i] = (RouteCard) routeCardDeck.pop();
            if(routeCardDeck.isEmpty()) {
                routeCardDeck = shuffle(routeCardDiscard);
                routeCardDiscard = new ArrayList<>();
            }
        }
        return threeRoutes;
    }

    public Deque<Card> shuffle(List<Card> deck) {
        Collections.shuffle(deck);
        Deque<Card> shuffledDeck = new ArrayDeque<>();
        for(Card card : deck) {
            shuffledDeck.add(card);
        }
        return shuffledDeck;
    }
}