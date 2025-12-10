import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Opponent extends Player{

    private Board board;
    private PriorityQueue<RoutePath> tracksNeeded;
    private boolean desperate = false;

    public Opponent(Board board, String name) {
        super(name);
        this.board = board;
        tracksNeeded = new PriorityQueue<>();
    }

    public void setDesperation(boolean desperation) {
        desperate = desperation;
    }

    public void getRecommendedMove(int movesLeft) {
        //1. if player has completed all routes:
        if(tracksNeeded.isEmpty() && movesLeft >= 2) {
            takeRouteCards(1);
            System.out.println("Bot took route cards");
        }
        //2. if able, play a track:
        else if(decideWhichTrackToBuy() && movesLeft >= 2) {
            System.out.println("Bot played track");
        }
        //3. if not able, 
        else {
            decideWhichCardToTake();
            System.out.println("Bot drew cards");
        }
    }

    private boolean decideWhichTrackToBuy() {
        //if opponent can purchase priority track, buy that one
        //else, check priority queue for any tracks the opponent can buy and buy them
        for(RoutePath route : tracksNeeded) {
            for(Track track : route.getTracks()) {
                if(board.buildTrain(this, track.startCity, track.endCity, track.color)) {
                    removeAllInstancesOfTrack(track);
                    if(route.getTracks().isEmpty()) {
                        tracksNeeded.remove(route);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void removeAllInstancesOfTrack(Track track) {
        for(RoutePath route : tracksNeeded) {
            route.removeTrack(track);
        }
    }

    private void decideWhichCardToTake() {
        //if desperate, check shop for wilds and take if able
        //if shop has priority color, take that color from the shop
        //else, check the shop for any other lower priority colors and take if available
        //else, draw from the top of the deck
        if(desperate && getMovesLeft() >= 2) {
            for(int i = 0; i < 5; i++) {
                if(board.viewShop()[i].color == TrainColor.WILD) {
                    board.drawTrainCardFromShop((Player) this, i);
                    makeMoves(2);
                }
            }
        } else if(hasActions()) {
            for(RoutePath route : tracksNeeded) {
                for(Track track : route.getTracks()) {
                    for(int i = 0; i < 5; i++) {
                        if(track.color == board.viewShop()[i].color && hasActions()) {
                            board.drawTrainCardFromShop((Player) this, i);
                            makeMoves(1);
                            break;
                        }
                    }
                }
            }
        } else {
            board.drawTopTrainCard((Player) this);
            makeMoves(1);
        }
    }

    public void takeOpponentTurn() {
        while(hasActions()) {
            getRecommendedMove(getMovesLeft());
        }
    }

    public void opponentSetUp() {
        for(int i = 0; i < 4; i++) {
            board.drawTopTrainCard(this);
        }
        takeRouteCards(2);
        for(RouteCard card : getRoutes()) {
            addRouteToPQ(card);
        }
    }

    private void addRouteToPQ(RouteCard card) {
        List<Track> tracks = findBestPath(card.startCity, card.endCity);
        if(tracks != null) {
            tracksNeeded.add(new RoutePath(tracks, card.pointValue));
        }
    }

    private void takeRouteCards(int minimum) {
        RouteCard[] routeCards = board.viewThreeRoutes();
        if(desperate) {
            drawLowestValue(routeCards);
        } else {
            int cardsDrawn = 0;
            for(RouteCard route : routeCards) {
                if(takeRoute(route)) {
                    drawRouteCard(route);
                    addRouteToPQ(route);
                    cardsDrawn++;
                }
            }
            if(cardsDrawn == 0) {
                for(int i = 0; i < minimum; i++) {
                    drawLowestValue(routeCards);
                }
            }
        }
        makeMoves(2);
    }

    private void drawLowestValue(RouteCard[] routeCards) {
        RouteCard lowestScore = new RouteCard(null, null, 100);
        for(int i = 0; i < 3; i++) {
            if(routeCards[i].pointValue < lowestScore.pointValue) {
                lowestScore = routeCards[i];
                routeCards[i] = new RouteCard(null, null, 100);
            }
        }
        drawRouteCard(lowestScore);
        addRouteToPQ(lowestScore);
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

    // private static void printCityPath(List<Integer> path, Board gameBoard) {
    //     for(Integer index : path) {
    //         System.out.println(gameBoard.getCityFromIndex(index));
    //     }
    //     System.out.println("--*--*--*--*--*--");

    // }
    
    // public static void main(String[] args) {
    //     Board gameBoard = new Board();
    //     printCityPath(gameBoard.dijkstraSearch(City.CALGARY, City.SANFRAN), gameBoard);
    //     printCityPath(gameBoard.dijkstraSearch(City.SANFRAN, City.CALGARY), gameBoard);
    //     printCityPath(gameBoard.dijkstraSearch(City.PHOENIX, City.MONTREAL), gameBoard);
    //     printCityPath(gameBoard.dijkstraSearch(City.MONTREAL, City.PHOENIX), gameBoard);
    //     printCityPath(gameBoard.dijkstraSearch(City.VANCOUVER, City.MIAMI), gameBoard);
    //     gameBoard.removeTrack(gameBoard.getIndexFromCity(City.HELENA), gameBoard.getIndexFromCity(City.SEATTLE));
    //     gameBoard.removeTrack(gameBoard.getIndexFromCity(City.VANCOUVER), gameBoard.getIndexFromCity(City.CALGARY));
    //     gameBoard.removeTrack(gameBoard.getIndexFromCity(City.VANCOUVER), gameBoard.getIndexFromCity(City.SEATTLE));
    //     gameBoard.removeTrack(gameBoard.getIndexFromCity(City.VANCOUVER), gameBoard.getIndexFromCity(City.SEATTLE));
    //     printCityPath(gameBoard.dijkstraSearch(City.MIAMI, City.VANCOUVER), gameBoard);
    // }

}