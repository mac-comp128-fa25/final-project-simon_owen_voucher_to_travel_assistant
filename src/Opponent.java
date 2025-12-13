import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

public class Opponent extends Player{

    private Board board;
    private PriorityQueue<RoutePath> tracksNeeded;
    private boolean desperate = false;
    private TrainColor universalWildColor = TrainColor.WILD;

    public Opponent(Board board, String name) {
        super(name);
        this.board = board;
        tracksNeeded = new PriorityQueue<>();
    }

    /**
     * Draws an opening hand and picks route cards according to the rules of Ticket to Ride
     */
    public void opponentSetUp() {
        for(int i = 0; i < 4; i++) {
            board.drawTopTrainCard(this);
        }
        takeRouteCards(2);
    }

    /**
     * @param desperation sets this opponent's desperation to desperate
     */
    public void setDesperation(boolean desperation) {
        desperate = desperation;
    }


    /**
     * Has this opponent perform a move based on the number of actions it has left
     */
    public void takeOpponentTurn() {
        while(hasActions()) {
            getRecommendedMove(getMovesLeft());
        }
    }

    /**
     * Checks if routes have been blocked by another player
     * @param boughtTrack a track that another player bought
     */
    public void recheckRoutes(Track boughtTrack) {
        for(RoutePath route : tracksNeeded) {
            for(Track track : route.getTracks()) {
                if(track.equals(boughtTrack)) {
                    for(Track newTrack : reroute(track.startCity, track.endCity)) {
                        route.addTrack(newTrack);
                    }
                }
            }
        }
        removeAllInstancesOfTrack(boughtTrack);
    }

    private void getRecommendedMove(int movesLeft) {
        //1. if player has completed all routes:
        if(tracksNeeded.isEmpty() && movesLeft >= 2 && board.checkRouteDeck() && !desperate) {
            takeRouteCards(1);
            makeMoves(2);
            System.out.println("Bot took route cards");
        }
        //2. if able, play a track:
        else if(decideWhichTrackToBuy()) {
            makeMoves(2);
            System.out.println("Bot played track: "+ getLastTrackBought());
        } else if(tracksNeeded.isEmpty() && buyRandomTrack()) {
            makeMoves(2);
            System.out.println("Bot played track: "+ getLastTrackBought());
        }
        //3. if not able, 
        else if(board.checkTrainCardDeck()) {
            TrainColor cardColor = decideWhichCardToTake();
            System.out.println("Bot drew a " + cardColor + " card");
        } else if(movesLeft == 2 && buyRandomTrack()) {
            makeMoves(2);
        } else {
            makeMoves(movesLeft);
            System.out.println("Bot passes the turn because its stupid");
        }
    }

    private boolean decideWhichTrackToBuy() {
        //if opponent can purchase priority track, buy that one
        //else, check priority queue for any tracks the opponent can buy and buy them
        HashSet<TrainColor> savedColors = new HashSet<>();
        if(getMovesLeft() < 2) {
            return false;
        }
        for(RoutePath route : tracksNeeded) {
            for(Track track : route.getTracks()) {
                if(track.color != TrainColor.WILD) {
                    if(!savedColors.contains(track.color)) {
                        if(board.buildTrain(this, track.startCity, track.endCity, track.color)) {
                            removeAllInstancesOfTrack(track);
                            if(route.getTracks().isEmpty()) {
                                tracksNeeded.remove(route); // If the route is complete we should add to our completed routes set
                                getCompletedRoutes().add(route);
                            }
                            return true;
                        } else {
                            savedColors.add(track.color);
                            if(savedColors.size() == 9) {
                                return false;
                            }
                        }
                    }
                } else {
                    if(board.buildTrain(this, track.startCity, track.endCity, universalWildColor)) {
                        removeAllInstancesOfTrack(track);
                        if(route.getTracks().isEmpty()) {
                            tracksNeeded.remove(route);
                        }
                        return true;
                    }
                }
            }
        }
        setUniversalColor(savedColors);
        return false;
    }

    private boolean buyRandomTrack() {
        List<Track> remainingTracks = board.getAvailableTracks();
        Collections.sort(remainingTracks, new TrackComparator());
        for(Track smallTrack : remainingTracks) {
            if(board.buildTrain(this, smallTrack.startCity, smallTrack.endCity, universalWildColor)) {
                return true;
            }
        }
        return false;
    }

    private void setUniversalColor(HashSet<TrainColor> savedColors) {
        int highestCount = -1;
        for(TrainColor color : getHand().keySet()) {
            if(!savedColors.contains(color) && color != TrainColor.WILD) {
                if(getHand().get(color) > highestCount) {
                    highestCount = getHand().get(color);
                    universalWildColor = color;
                }
            }
        }
    }

    private void removeAllInstancesOfTrack(Track track) {
        for(RoutePath route : tracksNeeded) {
            route.removeTrack(track);
        }
    }

    private TrainColor decideWhichCardToTake() {
        //if desperate, check shop for wilds and take if able
        //if shop has priority color, take that color from the shop
        //else, check the shop for any other lower priority colors and take if available
        //else, draw from the top of the deck
        if(desperate && getMovesLeft() >= 2) {
            for(int i = 0; i < 5; i++) {
                if(board.viewShop()[i].color == TrainColor.WILD) {
                    board.drawTrainCardFromShop((Player) this, i);
                    makeMoves(2);
                    return TrainColor.WILD;
                }
            }
        } else if(hasActions()) {
            for(RoutePath route : tracksNeeded) {
                for(Track track : route.getTracks()) {
                    for(int i = 0; i < 5; i++) {
                        if(track.color == board.viewShop()[i].color && hasActions()) {
                            board.drawTrainCardFromShop((Player) this, i); // TODO: THE BOT WILL DRAW A WILD CARD HERE IF IT HAS A WILD TRACK IT NEEDS TO BUY, this only costs it 1 action and it does not need wilds to buy wild tracks
                            makeMoves(1);
                            return track.color;
                        }
                    }
                }
            }
        }
        TrainColor cardColor = board.drawTopTrainCard((Player) this);
        makeMoves(1);
        return cardColor; // How do we know which card was removed? Should we have draw top train card return the color of the card? 
    }

    private void addRouteToPQ(RouteCard card) {
        List<Track> tracks = findBestPath(card.startCity, card.endCity);
        if(tracks != null) {
            tracksNeeded.add(new RoutePath(card.startCity, card.endCity, tracks, card.pointValue));
        }
    }

    private void takeRouteCards(int minimum) {
        System.out.println("Opponent drew route cards");
        List<RouteCard> routeCards = board.viewThreeRoutes();
        if(desperate) {
            drawLowestValue(routeCards);
        } else {
            int cardsDrawn = 0;
            Iterator<RouteCard> routeIterator = routeCards.iterator();
            while(routeIterator.hasNext()) {
                RouteCard route = routeIterator.next();
                System.out.println(route);
                if(takeRoute(route)) {
                    drawRouteCard(route);
                    addRouteToPQ(route);
                    routeIterator.remove();
                    cardsDrawn++;
                }
            }
            if(cardsDrawn == 0) {
                for(int i = 0; i < minimum; i++) {
                    drawGreatestValue(routeCards);
                }
            }
        }
        board.discardUndrawnRoutes(routeCards);
    }

    private void drawLowestValue(List<RouteCard> routeCards) {
        RouteCard lowestScore = new RouteCard(null, null, 100);
        for(RouteCard route : routeCards) {
            if(route.pointValue < lowestScore.pointValue) {
                lowestScore = route;
            }
        }
        routeCards.remove(lowestScore);
        drawRouteCard(lowestScore);
        addRouteToPQ(lowestScore);
    }

    private void drawGreatestValue(List<RouteCard> routeCards) {
        RouteCard greatestScore = new RouteCard(null, null, 0);
        for(RouteCard route : routeCards) {
            if(route.pointValue > greatestScore.pointValue) {
                greatestScore = route;
            }
        }
        routeCards.remove(greatestScore);
        drawRouteCard(greatestScore);
        addRouteToPQ(greatestScore);
    }

    private boolean takeRoute(RouteCard card) {
        List<Track> tracks = findBestPath(card.startCity, card.endCity);
        if(tracks == null) {
            return false;
        }
        int overlap = calculateOverlap(card);
        if(overlap >= tracks.size() - 2) {
            return true;
        } else {
            return false;
        }
    }

    private int calculateOverlap(RouteCard route) {
        //for each track in tracksNeeded, if route contains that track, increment by 1
        int overlap = 0;
        for(RoutePath path : tracksNeeded) {
            for(Track track1 : path.getTracks()) {
                for(Track track2 : findBestPath(route.startCity, route.endCity)) {
                    if(track1.equals(track2)) {
                        overlap++;
                    }
                }
            }
        }
        return overlap;
    }


    private List<Track> reroute(City startCity, City endCity) {
        return findBestPath(endCity, startCity);
    }

    private List<Track> findBestPath(City firstCity, City lastCity) {
        //side note that this method will not consider tracks the opponent already owns, leading to inefficient play

        List<Integer> cities = board.dijkstraSearch(firstCity, lastCity);
        if(cities.size() == 1) {
            return null;
        }
        List<Track> tracks = new ArrayList<>();
        Integer startCity = null;
        Integer endCity = null;
        for(Integer city : cities) {
            endCity = city;
            if(startCity == null) {
                startCity = endCity;
                continue;
            } else {
                tracks.add(board.getTrackFromCities(startCity, endCity));
                startCity = endCity;
            }
        }
        return tracks;
    }


}