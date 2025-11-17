import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class Player {

    private int score;
    private Map<TrainCard,Integer> hand;
    private Set<RouteCard> routes;
    private int trains = 45;

    public Player() {
        hand = new HashMap<>();
    }

    public int getScore() {
        return score;
    }
    
    public void addToScore(int points) {
        score += points;
    }

    public Map<TrainCard,Integer> getHand() {
        return hand;
    }

    public void drawTrainCard(TrainCard card) {
        if(hand.containsKey(card)) {
            hand.put(card, hand.get(card) + 1);
        } else {
            hand.put(card, 1);
        }
    }

    public void drawRouteCard(RouteCard card) {
        routes.add(card);
    }

    public Boolean playTrainCards(TrainCard card, int quantity) {
        if(hand.get(card) != null && hand.get(card) >= quantity) {
            hand.put(card, hand.get(card) - quantity);
            return true;
        }
        return false;
    }

    public Boolean spendTrains(int quantity) {
        if(trains >= quantity) {
            trains -= quantity;
            return true;
        }
        return false;
    }
}