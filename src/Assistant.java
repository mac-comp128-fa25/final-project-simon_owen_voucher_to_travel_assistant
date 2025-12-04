import java.util.List;
import java.util.PriorityQueue;

public class Assistant {

    private Player player;
    private Board board;
    private PriorityQueue<RoutePath> tracksNeeded;
    private boolean desperate = false;

    public Assistant(Player player, Board board) {
        this.player = player;
        this.board = board;
    }

    public String getRecommendedMove() {
        //1. if player has completed all routes and is not desperate:
        if(allRoutesCompleted() && !desperate) {
            return playDirty();
        }
        //1.5 if player has completed all routes and is desperate:
        else if(allRoutesCompleted() && desperate) {
            decideWhichRoutesToTake();
        }
        //2. if able, play a track:
        else if(canPlayTrack()) {
            decideWhichTrackToBuy();
        }
        //3. if not able, 
        else {
            decideWhichCardToTake();
        }
        return "WIN";
    }

    private boolean allRoutesCompleted() {
        return false;
    }

    private boolean canPlayTrack() {
        return false;
    }

    private String playDirty() {
        //try and build in other players' ways
        return "MUAHAHHA";
    }

    private String decideWhichRoutesToTake() {
        //find best path of route and crosscheck with existing tracks needed to count overlap. Higher
        //overlap is good
        //if overlap is above a certain threshhold, take the route
        //if no routes are above that threshhold and assistant is desperate, take the lowest value route
        //if assistant isn't desperate, take the highest overlap route
        return "ALL OF THEM";
    }

    private String decideWhichTrackToBuy() {
        //if player can purchase priority track, buy that one
        //else, check priority queue for any tracks the player can buy and buy them
        return "ALL OF THEM";
    }

    private String decideWhichCardToTake() {
        TrainColor priorityColor = tracksNeeded.poll().poll().color;
        //if shop has priority color, take that color from the shop
        //else, check the shop for any other lower priority colors and take if available
        //else, if desperate, check shop for wilds and take if able
        //else, draw from the top of the deck
        return "ALL OF THEM";
    }

    private List<Integer> findBestPath(City firstCity, City lastCity) {
        return board.dijkstraSearch(firstCity, lastCity);
    }

    private static void printCityPath(List<Integer> path, Board gameBoard) {
        for(Integer index : path) {
            System.out.println(gameBoard.getCityFromIndex(index));
        }
        System.out.println("--*--*--*--*--*--");

    }
    
    public static void main(String[] args) {
        Board gameBoard = new Board();
        printCityPath(gameBoard.dijkstraSearch(City.CALGARY, City.SANFRAN), gameBoard);
        printCityPath(gameBoard.dijkstraSearch(City.SANFRAN, City.CALGARY), gameBoard);
        printCityPath(gameBoard.dijkstraSearch(City.PHOENIX, City.MONTREAL), gameBoard);
        printCityPath(gameBoard.dijkstraSearch(City.MONTREAL, City.PHOENIX), gameBoard);
        printCityPath(gameBoard.dijkstraSearch(City.VANCOUVER, City.MIAMI), gameBoard);
        gameBoard.removeTrack(gameBoard.getIndexFromCity(City.HELENA), gameBoard.getIndexFromCity(City.SEATTLE));
        gameBoard.removeTrack(gameBoard.getIndexFromCity(City.VANCOUVER), gameBoard.getIndexFromCity(City.CALGARY));
        gameBoard.removeTrack(gameBoard.getIndexFromCity(City.VANCOUVER), gameBoard.getIndexFromCity(City.SEATTLE));
        gameBoard.removeTrack(gameBoard.getIndexFromCity(City.VANCOUVER), gameBoard.getIndexFromCity(City.SEATTLE));
        printCityPath(gameBoard.dijkstraSearch(City.MIAMI, City.VANCOUVER), gameBoard);

    }

}