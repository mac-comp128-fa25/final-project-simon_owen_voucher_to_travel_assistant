import java.util.List;

public class Assistant {

    private static void printCityPath(List<Integer> path, Board gameBoard) {
        for(Integer index : path) {
            System.out.println(gameBoard.getCityFromIndex(index));
        }
        System.out.println("--*--*--*--*--*--");

    }
    public static void main(String[] args) {
        Board gameBoard = new Board();
        Player player = new Player();
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