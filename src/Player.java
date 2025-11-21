import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
public class Player {

    private int score;
    private Map<TrainColor,Integer> hand;
    private Set<RouteCard> routes;
    private int trains = 45;
    private int moves = 2;

    public Player(Board board) {
        hand = new HashMap<>();
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

    // Is it better to separate TrainCard from Color even though they are basically the same?
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

    public boolean buyTrack(TrainColor color, int quantity) {
        if(hand.get(color) != null && hand.get(color) >= quantity && spendTrains(quantity)) {
            hand.put(color, hand.get(color) - quantity);
            return true;
        } 
        return false;
        // Maybe throw exception? Add to discard pile <- use board somehow?
    }

    private boolean spendTrains(int quantity) {
        if(trains >= quantity) {
            trains -= quantity;
            return true;
        }
        return false;
    }
}