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
    private Board board;
    private int moves = 2;

    public Player(Board board) {
        hand = new HashMap<>();
        this.board = board;
    }

    public void takeTurn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do?");
        if(moves == 2) {
            System.out.println("Play Trains");
            System.out.println("");
            System.out.println("Draw Train Cards");
            System.out.println("");
            System.out.println("Draw Route Cards");
            String move = sc.nextLine().toLowerCase();
            if(move.equals("play trains")) {
                playTrains(sc);
            } else if(move.equals("draw train cards")) {
                drawTrainCard(sc);
            } else if(move.equals("draw route cards")) {
                drawRouteCards(sc);
            } else {
                System.out.println("Invalid Input");
            }
        } else if(moves == 1) {
            System.out.println("Draw Train Cards");
            String move = sc.nextLine().toLowerCase();
            if(move.equals("draw train cards")) {
                drawTrainCard(sc);
            } else {
                System.out.println("Invalid Input");
            }
        } else {
            System.out.println("Turn Over");
        }
    }

    public void playTrains(Scanner sc) {
        System.out.println("Where would you like to place trains? Please input 2 city names like 'Seattle, Washington'");
        String input = sc.nextLine().toUpperCase();
        String[] cities = input.split(", ");
        City city1 = City.valueOf(cities[0]);
        City city2 = City.valueOf(cities[1]);
        List<Track> tracks = board.getTracks(city1, city2);
        if(tracks.size() == 2) {
            System.out.println("There are two tracks between these two cities");
            for(Track track : tracks) {
                System.out.println(track.color);
            }
            System.out.println("Which color would you like?");
            TrainColor colorOfTrack = TrainColor.valueOf(sc.nextLine().toUpperCase());
            if(colorOfTrack == TrainColor.WILD) {

            } else {
                for(TrainColor color : hand.keySet()) {
                    if(color == colorOfTrack) {
                        if(hand.get(color) + hand.get(TrainColor.WILD) >= tracks.getFirst().length) {
                            playTrainCards(colorOfTrack, tracks.getFirst().length);
                        }
                    }
                }
            }
        }
        
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

    public void drawTopTrainCard(TrainColor color) {
        board.drawTopTrainCard();
        if(hand.containsKey(color)) {
            hand.put(color, hand.get(color) + 1);
        } else {
            hand.put(color, 1);
        }
    }

    public boolean drawShopTrainCard(TrainColor color) {
        TrainColor card = board.drawTrainCardFromShop(this, color);
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

    public void playTrainCards(TrainColor color, int quantity) {
        if(hand.get(color) != null && hand.get(color) >= quantity) {
            hand.put(color, hand.get(color) - quantity);
            board.spendTrainCards(color, quantity);
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