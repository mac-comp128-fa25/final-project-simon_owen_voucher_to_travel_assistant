import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private int score;
    private Map<TrainColor,Integer> hand;
    private Set<RouteCard> incompletedRoutes;
    private List<Track> ownedTracks;
    private Map<Integer,Integer> scoringGuide;
    private int trains = 45;
    private int moves = 2;
    private String name;
    private Set<RouteCard> completedRoutes;

    public Player(String name) {
        this.name = name;
        initializeHand();
        incompletedRoutes = new HashSet<>();
        completedRoutes = new HashSet<>();
        ownedTracks = new ArrayList<>();
        makeScoringGuide();
    }


    /**
     * @return list of tracks the player owns
     */
    public List<Track> viewOwnedTracks() {
        return ownedTracks;
    }

    /**
     * @return set of all routes the player has not completed
     */
    public Set<RouteCard> getRoutes() {
        return incompletedRoutes;
    }

    /**
     * @return set of all routes the player has completed
     */
    public Set<RouteCard> getCompletedRoutes() {
        return completedRoutes;
    }

    /**
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * @return trains the player has left
     */
    public int getTrainsLeft() {
        return trains;
    }

    /**
     * @return player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return true if player has actions left, false otherwise
     */
    public boolean hasActions() {
        return moves > 0;
    }

    /**
     * @return amount of moves player has left
     */
    public int getMovesLeft() {
        return moves;
    }

    /**
     * @return Players hand, a hashmap where train card colors -> amount in hand
     */
    public Map<TrainColor,Integer> getHand() {
        return hand;
    }

    /**
     * resets player's moves
     */
    public void endTurn() {
        moves = 2;
    }

    /**
     * @param actionsSpent the amount of moves to use
     */
    public void makeMoves(int actionsSpent) {
        moves -= actionsSpent;
    }
    

    /**
     * @param points amount to increase the total score by
     */
    public void addToScore(int points) {
        score += points;
    }

    /**
     * @param card route card to add to the players set of incomplete route cards
     */
    public void drawRouteCard(RouteCard card) {
        incompletedRoutes.add(card);
    }

    /**
     * Adds a track to player's list of owned tracks
     * @param startCity of track
     * @param endCity of track
     * @param length of track
     * @param color of track
     */
    public void buyTrack(City startCity, City endCity, int length, TrainColor color) {
        ownedTracks.add(new Track(startCity, endCity, length, color));
    }
    
    /**
     * @return last track the player bought
     */
    public Track getLastTrackBought() {
        return ownedTracks.getLast();
    }


    /**
     * @param card train card to put in the player's hand
     */
    public void drawTrainCard(TrainCard card) {
        hand.put(card.color, hand.get(card.color)+1);
    }

    /**
     * Determines if the player can buy a track
     * @param color of the track
     * @param quantity of color needed to buy the track
     * @param board to give spent cards to
     * @return true if the player can build the track, false otherwise
     */
    public boolean spendTrainCards(TrainColor color, int quantity, Board board) {
        if(hand.get(color) >= quantity && spendTrains(quantity)) {
            hand.put(color, hand.get(color) - quantity);
            board.addAmountToDiscard(quantity, color);
            return true;
        } else if(color != TrainColor.WILD && hand.get(color) + hand.get(TrainColor.WILD) >= quantity && spendTrains(quantity)) {
            int leftOver = quantity - hand.get(color);
            board.addAmountToDiscard(hand.get(color), color);
            hand.put(color, 0);
            hand.put(TrainColor.WILD, hand.get(TrainColor.WILD) - leftOver);
            board.addAmountToDiscard(leftOver, TrainColor.WILD);
            return true;
        } else if(hand.get(TrainColor.WILD) >= quantity && spendTrains(quantity)) {
            hand.put(TrainColor.WILD, hand.get(TrainColor.WILD) - quantity);
            board.addAmountToDiscard(quantity, TrainColor.WILD);
            return true;
        }
        return false;
    }


    /**
     * Calculates the final score of player based on tracks owned and route cards
     * @return the final score
     */
    public int calculateFinalScore() {
        for(RouteCard route : completedRoutes) {
            score += route.pointValue;
        }
        for(RouteCard route : incompletedRoutes) {
            score -= route.pointValue;
        }
        return score;
    }

    /**
     * Checks if this player has completed any of their routes, if so it moves the route from the set of incomplete routes to
     * the set of complete routes.
     */
    public void checkForCompletedRoutes() {
        Iterator<RouteCard> incompleteRoutesIterator = incompletedRoutes.iterator();
        while(incompleteRoutesIterator.hasNext()) {
            RouteCard route = incompleteRoutesIterator.next();
            City startCity = route.startCity; 
            for(Track track : ownedTracks) {
                if(track.startCity == startCity) {
                    if(checkForPathtoCity(startCity, route.endCity, track, new HashSet<>())) {
                        completedRoutes.add(route);
                        incompleteRoutesIterator.remove();
                        break;
                    }
                }
            }
        } 
    }

    /**
     * Recursive Helper method to find if there exists a path from one city to another given the player's tracks
     * @param prevCity The current city the algorithm is looking at, when first called this is the start city.
     * @param targetCity The city the path must end on
     * @param currentTrack The current track the program is looking at
     * @param usedTracks The set of all tracks already on the current path
     * @return true if a path exists prevCity -> targetCity, false otherwise.
     */
    public boolean checkForPathtoCity(City prevCity, City targetCity, Track currentTrack, HashSet<Track> usedTracks) {
        City nextCity = currentTrack.getOtherCity(prevCity);
        if(nextCity == targetCity) {
            return true;
        } else {
            usedTracks.add(currentTrack);
            for(Track track : ownedTracks) {
                if(track.hasCity(nextCity) && !usedTracks.contains(track)) {
                    if(checkForPathtoCity(nextCity, targetCity, track, usedTracks)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void initializeHand() {
        hand = new HashMap<>();
        for(TrainColor color : TrainColor.values()) {
            hand.put(color, 0);
        }
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
    
    private void gainPoints(int length) {
        score += scoringGuide.get(length);
    }

    private boolean spendTrains(int quantity) {
        if(trains >= quantity) {
            trains -= quantity;
            gainPoints(quantity);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //Testing checkRoutesCompleted
        Player testPlayer = new Player("Tester");
        RouteCard routeCard = new RouteCard(City.LA, City.LR, 17);
        RouteCard routeCard2 = new RouteCard(City.LA, City.HOUSTON, 13);
        testPlayer.drawRouteCard(routeCard);
        testPlayer.drawRouteCard(routeCard2);
        testPlayer.buyTrack(City.LA, City.SANFRAN, 3, TrainColor.PINK);
        testPlayer.buyTrack(City.SLC, City.SANFRAN, 5, TrainColor.WHITE);
        testPlayer.buyTrack(City.SLC, City.DENVER, 3, TrainColor.RED);
        testPlayer.buyTrack(City.OKC, City.DENVER, 4, TrainColor.RED);
        testPlayer.buyTrack(City.OKC, City.DALLAS, 2, TrainColor.WILD);
        testPlayer.buyTrack(City.HOUSTON, City.DALLAS, 1, TrainColor.WILD);
        testPlayer.buyTrack(City.LA, City.PHOENIX, 3, TrainColor.WILD);
        testPlayer.buyTrack(City.PHOENIX, City.SANTAFE, 3, TrainColor.WILD);
        testPlayer.buyTrack(City.SANTAFE, City.OKC, 3, TrainColor.BLUE);
        testPlayer.buyTrack(City.LR, City.OKC, 2, TrainColor.WILD);
        testPlayer.checkForCompletedRoutes();
        System.out.println(testPlayer.getCompletedRoutes());

    }
}