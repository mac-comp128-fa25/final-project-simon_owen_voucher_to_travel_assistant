import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private int score;
    private Map<TrainColor,Integer> hand;
    private Set<RouteCard> routes;
    private List<Track> ownedTracks;
    private Map<Integer,Integer> scoringGuide;
    private int trains = 45;
    private int moves = 2;
    private String name;
    private Assistant assistant;

    public Player(String name, Assistant assistant) {
        this.assistant = assistant;
        this.name = name;
        initializeHand();
        routes = new HashSet<>();
        ownedTracks = new ArrayList<>();
        makeScoringGuide();
    }

    public String getName() {
        return name;
    }

    private void initializeHand() {
        hand = new HashMap<>();
        for(TrainColor color : TrainColor.values()) {
            hand.put(color, 0);
        }
    }

    public Set<RouteCard> getRoutes() {
        return routes;
    }

    private void makeScoringGuide() {
        scoringGuide = new HashMap<>();
        scoringGuide.put(1,1);
        scoringGuide.put(2,2);
        scoringGuide.put(3,4);
        scoringGuide.put(4,7);
        scoringGuide.put(5,10);
        scoringGuide.put(6,15);
    }

    public List<Track> viewOwnedTracks() {
        return ownedTracks;
    }

    public int getTrainsLeft() {
        return trains;
    }

    public boolean hasActions() {
        return moves > 0;
    }

    public int getMovesLeft() {
        return moves;
    }

    public void endTurn() {
        moves = 2;
    }

    public void makeMoves(int actionsSpent) {
        moves -= actionsSpent;
    }

    public int getScore() {
        return score;
    }
    
    public void addToScore(int points) {
        score += points;
    }

    public Map<TrainColor,Integer> getHand() {
        return hand;
    }

    public void drawTrainCard(TrainCard card) {
        if(hand.containsKey(card.color)) {
            hand.put(card.color, hand.get(card.color)+1);
        } else {
            hand.put(card.color, 1);
        }
    }

    public void drawRouteCard(RouteCard card) {
        routes.add(card);
    }

    public void buyTrack(City startCity, City endCity, int length, TrainColor color) {
        ownedTracks.add(new Track(startCity, endCity, length, color));
    }

    public boolean spendTrainCards(TrainColor color, int quantity) {
        if(hand.get(color) >= quantity && spendTrains(quantity)) {
            hand.put(color, hand.get(color) - quantity);
            return true;
        } else if(hand.get(color) + hand.get(TrainColor.WILD) >= quantity && spendTrains(quantity)) {
            int leftOver = quantity - hand.get(color);
            hand.put(color, 0);
            hand.put(TrainColor.WILD, hand.get(TrainColor.WILD) - leftOver);
            return true;
        } else if(hand.get(TrainColor.WILD) >= quantity) {
            hand.put(TrainColor.WILD, hand.get(TrainColor.WILD) - quantity);
            return true;
        }
        return false;
    }

    private boolean spendTrains(int quantity) {
        if(trains >= quantity) {
            trains -= quantity;
            gainPoints(quantity);
            return true;
        }
        return false;
    }

    private void gainPoints(int length) {
        score += scoringGuide.get(length);
    }
}