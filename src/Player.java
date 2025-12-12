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

    public List<Track> viewOwnedTracks() {
        return ownedTracks;
    }

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
                    }
                }
            }
        } 
    }

    /**
     * Recursive Helper method to find if there exists a path from one city to another
     * @param prevCity The current city the algorithm is looking at, when first called this is the start city.
     * @param targetCity The city the path must end on
     * @param currentTrack The current track the program is looking at
     * @param usedTracks The set of all tracks already on the current path
     * @return true if a path exists prevCity -> targetCity, false otherwise.
     */
    private boolean checkForPathtoCity(City prevCity, City targetCity, Track currentTrack, HashSet<Track> usedTracks) {
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

    public Set<RouteCard> getRoutes() {
        return incompletedRoutes;
    }

    public Set<RouteCard> getCompletedRoutes() {
        return completedRoutes;
    }

    public String getName() {
        return name;
    }

    public int getTrainsLeft() {
        return trains;
    }

    public int getScore() {
        return score;
    }

    public boolean hasActions() {
        return moves > 0;
    }

    public int getMovesLeft() {
        return moves;
    }

    public Map<TrainColor,Integer> getHand() {
        return hand;
    }

    public void endTurn() {
        moves = 2;
    }

    public void makeMoves(int actionsSpent) {
        moves -= actionsSpent;
    }
    
    public void addToScore(int points) {
        score += points;
    }

    private void gainPoints(int length) {
        score += scoringGuide.get(length);
    }

    public void drawRouteCard(RouteCard card) {
        incompletedRoutes.add(card);
    }

    public void buyTrack(City startCity, City endCity, int length, TrainColor color) {
        ownedTracks.add(new Track(startCity, endCity, length, color));
    }
    
    public Track getLastTrackBought() {
        return ownedTracks.getLast();
    }

    public void drawTrainCard(TrainCard card) {
        if(hand.containsKey(card.color)) {
            hand.put(card.color, hand.get(card.color)+1);
        } else {
            hand.put(card.color, 1);
        }
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