import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class PlayerInteraction {

    private Board board;
    private Queue<Player> players;
    private Assistant assistant;

    public PlayerInteraction(int numberOfPlayers) {
        board = new Board();
        players = new ArrayDeque<>();
        for(int i = 0; i<numberOfPlayers; i++) {
            players.add(new Player());
        }
        assistant = new Assistant(players.peek(), board);
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        boolean gameOver = false;

        for(Player player : players) {
            setUp(player, sc);
        }
        board.initializeShop();


        while(!gameOver) {
            Player currentPlayer = players.remove();
            takeTurn(currentPlayer, sc);
            // Check for gameover conditions
            players.add(currentPlayer);
        }

    }


    private void setUp(Player player, Scanner sc) {
        for(int i = 0; i < 4; i++) {
            board.drawTopTrainCard(player);
        }

        RouteCard[] threeRoutesForPlayer = board.viewThreeRoutes();
        for(int i = 0; i < 3; i++) {
            System.out.println((i+1) + ". " + threeRoutesForPlayer[i]);
        }

        boolean atLeastTwoRouteCards = false;
        int routeCardsChosen = 0;
        while(!atLeastTwoRouteCards) {
            System.out.println("Which Route Card Would you Like to Choose? (1,2,3)");
            int routeCardChosen = sc.nextInt();
            if(routeCardChosen > 3 || routeCardChosen < 1) {
                System.out.println("NAH");
            } else if(threeRoutesForPlayer[routeCardChosen-1] == null) {
                System.out.println("You already choose this route goofy ahh");
            } else {
                player.drawRouteCard(threeRoutesForPlayer[routeCardChosen-1]);
                threeRoutesForPlayer[routeCardChosen-1] = null;
                routeCardsChosen++;
                if(routeCardsChosen == 2) {
                    System.out.println("Would you like to continue? (Y/N)");
                    String response = sc.nextLine();
                    if(!response.equals("Y")) {
                        atLeastTwoRouteCards = true;
                    }
                }
            }
        }

    }

    public void takeTurn(Player player, Scanner sc) {
        System.out.println("Hah");
    }


    private void displayBoardState() {
        System.out.println("WEEE");
    }


    public static void main(String[] args) {
        PlayerInteraction io = new PlayerInteraction(4);
        io.playGame();
    }





}
