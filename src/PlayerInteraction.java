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
            System.out.println("--------- New Player Setup ---------");
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

        boolean atLeastTwoRouteCards = false;
        int routeCardsChosen = 0;
        while(!atLeastTwoRouteCards) {
            for(int i = 0; i < 3; i++) {
                if(threeRoutesForPlayer[i] != null ) {
                    System.out.println((i+1) + ". " + threeRoutesForPlayer[i]);
                } else {
                    System.out.println((i+1) + ". ALREADY CHOSEN");
                }
                
            }
            System.out.print("Which Route Card Would you Like to Choose? (1,2,3) ");
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
                    System.out.print("Would you like to continue? (Y/N) ");
                    String response = sc.next();
                    if(!response.equals("Y")) {
                        atLeastTwoRouteCards = true;
                    }
                } else if(routeCardsChosen == 3) {
                    atLeastTwoRouteCards = true;
                }
            }
        }

    }

    public void takeTurn(Player player, Scanner sc) {
        while(player.hasActions()) {
            System.out.println("1. Draw a Train Card");
            System.out.println("2. Draw Three new Route Cards");
            System.out.println("3. Buy a Track");
            System.out.print("Which Action would you like to take? (1,2,3) ");
            int moveChosen = sc.nextInt();
            if(moveChosen == 1) {
                System.out.print("Would you like to take from the shop or from the deck? (1:Shop, 2:Deck) ");
                int cardActionChosen = sc.nextInt();
                if(cardActionChosen == 1) {
                    // TODO: Draw card from shop
                    player.makeMoves(1); // if not wild
                } else {
                    board.drawTopTrainCard(player);
                    player.makeMoves(1);
                }
            } else if(moveChosen == 2 ) {
                /* TODO: Draw three, player can choose 1-3 of them to put in hand. Split up code from setup() to reuse main ideas 
                *  I.e. write a method that has a player draw three route cards and be forced to choose n of them. This is basically the same
                *  as what we have just the number of cards that the player needs to take changes.   
                */ 
                player.makeMoves(2);
            } else if(moveChosen == 3) {
                System.out.print("In which city will your train track start? (City Name) ");
                City startCity = City.valueOf(sc.next()); // IDK if this works, suhas said we should use a hashmap.
                System.out.print("In which city will your train track end? (City Name) ");
                City endCity = City.valueOf(sc.next());
                System.out.print("What color? ");
                TrainColor color = TrainColor.valueOf(sc.next());
                board.buildTrain(player, startCity, endCity, color); 
                //TODO: Check if this is a valid move, buildtrain should return false if this is the case
                player.makeMoves(2);
            }
        }
    }


    private void displayBoardState() {
        System.out.println("WEEE");
    }


    public static void main(String[] args) {
        PlayerInteraction io = new PlayerInteraction(2);
        io.playGame();
    }





}
