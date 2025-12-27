import java.util.Scanner;
import java.util.List;

public class PathFinder {
    public static void main(String[] args) {
        Board board = new Board();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose First City:");
        City startCity = City.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Choose Second City:");
        City endCity = City.valueOf(sc.nextLine().toUpperCase());
        sc.close();

        List<Integer> cityIndices = board.dijkstraSearch(startCity, endCity);
        int counter = 0;
        for(int index : cityIndices) {
            counter++;
            System.out.println(counter + " -> " + board.getGraph().indexCityMap.get(index));
        }
    }
}
