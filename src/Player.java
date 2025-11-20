import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class Player {

    private int score;
    private Map<TrainCard,Integer> hand;
    private Set<RouteCard> routes;
    private int trains = 45;
    private Board board;
    private int moves = 2;

    public Player(Board board) {
        hand = new HashMap<>();
        this.board = board;
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

    public void drawTopTrainCard(TrainCard card) {
        board.drawTopTrainCard();
        if(hand.containsKey(card)) {
            hand.put(card, hand.get(card) + 1);
        } else {
            hand.put(card, 1);
        }
    }

    public boolean drawShopTrainCard(TrainColor color) {
        TrainCard card = board.drawTrainCardFromShop(this, color);
        if(card != null) {
            if(hand.containsKey(card)) {
                hand.put(card, hand.get(card) + 1);
                return true;
            } else {
                hand.put(card, 1);
                return true;
            }
        }
        return false;
    }

    public void drawRouteCard(RouteCard card) {
        routes.add(card);
    }

    public void playTrainCards(TrainCard card, int quantity) {
        if(hand.get(card) != null && hand.get(card) >= quantity) {
            hand.put(card, hand.get(card) - quantity);
            board.spendTrainCards(card, quantity);
        }
    }

    public Boolean spendTrains(int quantity) {
        if(trains >= quantity) {
            trains -= quantity;
            return true;
        }
        return false;
    }
}