import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class PlayerInteraction {

    private Board board;
    private Queue<Player> players;
    private Opponent opponent;
    private boolean opponentsDesperate = false;

    public PlayerInteraction(int numberOfPlayers, int numberOfBots) {
        board = new Board();
        players = new ArrayDeque<>();
        for(int i = 0; i<numberOfPlayers; i++) {
            players.add(new Player("Player: " + i+1));
        }
        for(int i = 0; i<numberOfBots; i++) {
            opponent = new Opponent(board, "OP: " + i+1);
        }
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        boolean gameOver = false;

        for(Player player : players) {
            if(player instanceof Opponent) {
                opponent = (Opponent) player;
                opponent.opponentSetUp();
            } else {
                System.out.println("--------- New Player Setup ---------");
                setUp(player, sc);
            }
        }
        board.initializeShop();

        //while loop for the actual gameplay
        while(!gameOver) {
            checkDesperation();
            Player currentPlayer = players.remove();
            if(currentPlayer instanceof Opponent) {
                opponent = (Opponent) currentPlayer;
                opponent.setDesperation(opponentsDesperate);
                opponent.takeOpponentTurn();
                players.add(currentPlayer);
            } else {
                displayBoardState();
                System.out.println(currentPlayer.getName());
                takePlayerTurn(currentPlayer, sc);
                // Check for gameover conditions
                players.add(currentPlayer);
            }
        }
    }

    private void checkDesperation() {
        for(Player player : players) {
            if(player.getTrainsLeft() <= 10) {
                opponentsDesperate = true;
            }
        }
    }

    private void setUp(Player player, Scanner sc) {
        for(int i = 0; i < 4; i++) {
            board.drawTopTrainCard(player);
        }
        takeRouteCards(player, sc, 2);
    }

    private void takeRouteCards(Player player, Scanner sc, int requiredCards) {
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
                if(routeCardsChosen >= requiredCards) {
                    System.out.print("Would you like to continue? (Y/N) ");
                    String response = sc.next();
                    if(!response.toUpperCase().equals("Y")) {
                        atLeastTwoRouteCards = true;
                    }
                } else if(routeCardsChosen == 3) {
                    atLeastTwoRouteCards = true;
                }
            }
        }
    }

    public void takePlayerTurn(Player player, Scanner sc) {
        TrainCard[] shop = board.viewShop();
        while(player.hasActions()) {
            System.out.println("1. Draw a Train Card");
            System.out.println("2. Draw Three new Route Cards");
            System.out.println("3. Buy a Track");
            System.out.println("4. View Shop");
            System.out.println("5. View Hand");
            System.out.println("6. View Routes");
            System.out.print("Which Action would you like to take? (1,2,3,4,5,6) ");
            int moveChosen = sc.nextInt();
            if(moveChosen == 1) {
                System.out.print("Would you like to take from the shop or from the deck? (1:Shop, 2:Deck) ");
                int cardActionChosen = sc.nextInt();
                if(cardActionChosen == 1) {
                   System.out.println("Which card would you like to take? (1-5) ");
                    displayShop(shop);
                    int shopCardChosen = sc.nextInt();
                    if(shopCardChosen < 6 && shopCardChosen > 0) {
                        if(shop[shopCardChosen-1].color == TrainColor.WILD && player.getMovesLeft() == 1) {
                            System.out.println("You may not take a wild if you have already taken a card");
                            continue;
                        } else if(shop[shopCardChosen-1].color == TrainColor.WILD) {
                            board.drawTrainCardFromShop(player, shopCardChosen-1);
                            player.makeMoves(2);
                        } else {
                            board.drawTrainCardFromShop(player, shopCardChosen-1);
                            player.makeMoves(1);
                        }
                    } else {
                        System.out.println("NAH");
                    }
                } else {
                    board.drawTopTrainCard(player);
                    player.makeMoves(1);
                }
            } else if(moveChosen == 2) {
                if(player.getMovesLeft() >= 2) {
                    takeRouteCards(player, sc, 1);
                    player.makeMoves(2);
                } else {
                    System.out.println("You don't have enough actions for this, try again next turn");
                }
            } else if(moveChosen == 3 && player.getMovesLeft() >= 2) {
                if(player.getMovesLeft() >= 2) {

                    System.out.print("In which city will your train track start? (City Name) ");
                    City startCity = City.valueOf(sc.next().toUpperCase());

                    System.out.print("In which city will your train track end? (City Name) ");
                    City endCity = City.valueOf(sc.next().toUpperCase());

                    System.out.print("What color? ");
                    TrainColor color = TrainColor.valueOf(sc.next().toUpperCase());

                    if(!board.buildTrain(player, startCity, endCity, color)) {
                        System.out.println("You cannot buy this track, try again");
                    } else {
                        System.out.println("You now own this track");
                        player.makeMoves(2);
                        for(Player otherPlayer : players) {
                            if(otherPlayer instanceof Opponent) {
                                Opponent otherOpponent = (Opponent) otherPlayer;
                                otherOpponent.recheckRoutes(player.getLastTrackBought());
                            }
                        }
                    }
                } else {
                    System.out.println("You don't have enough actions for this, try again next turn");
                }
            } else if(moveChosen == 4) {
                displayShop(shop);
            } else if(moveChosen == 5) {
                displayHand(player);
            } else if(moveChosen == 6) {
                displayRoutes(player);
            }
        }
        player.endTurn();
    }

    private void displayBoardState() {
        for(Player player : players) {
            displayTrainsLeft(player);
            displayTrackOwnership(player);
            displayScore(player);
        }
        displayShop(board.viewShop());
    }

    private void displayTrackOwnership(Player player) {
        //print list of owned tracks
        for(Track track : player.viewOwnedTracks()) {
            System.out.println(track);
        }
    }

    private void displayTrainsLeft(Player player) {
        //print trains left
        System.out.println(player.getTrainsLeft());
    }

    private void displayScore(Player player) {
        //print score
        System.out.println(player.getScore());
    }

    private void displayShop(TrainCard[] shop) {
        //print shop
        for(int i = 0; i < 5; i++) {
            System.out.println(i+1 + ". " + shop[i].color);
        }
    }

    private void displayHand(Player player) {
        //print player's hand
        for(TrainColor key : player.getHand().keySet()) {
            System.out.println(key + " : " + player.getHand().get(key));
        }
    }

    private void displayRoutes(Player player) {
        //print player's routes
        for(RouteCard route : player.getRoutes()) {
            System.out.println(route);
        }
    }

    public static void main(String[] args) {
        PlayerInteraction io = new PlayerInteraction(1, 1);
        io.playGame();
    }
}