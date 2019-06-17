package com.fundesplai.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TEXT USER INTERFACE
 */
public class TUI {

    private static Scanner scan;

    //Very tricky variables for control when to print:
    //a line giving the numerals of the cells of an axis
    private static boolean downValuesFlag;
    //the position within a given axis containing a section or a group of sections representing various dimensions of the space
    private static int downAxisFlag;

    public static List<Axis> askDimensions() {

        scan = new Scanner(System.in);
        List<Axis> axis = new ArrayList<Axis>();
        
        System.out.println("¿How many dimensions would you like to your game? (from 2 up)");
        System.out.println("Be advised of a tiresome representation of the game board if the dimensions are over 3 or 4.");
        int dims = scan.nextInt();
        while (dims < 2) {

            System.out.println("The game must have at least 2 dimensions. Retype the number: ");
            dims = scan.nextInt();
        }

        for (int i = 0 ; i < dims ; i++) {

            System.out.println("¿Which size do you want to '" + AxisNameGenerator.get(i) + "' axis?");
            int size = scan.nextInt();
            while (size < 3) {

                System.out.println("Any dimension must have at least 3 units of size. Retype the number: ");
                size = scan.nextInt();
            }
            axis.add(new Axis(size, i));
        }

        //scan.close();
        return axis;
    }

    public static Position askPosition(Space space) {
        
        scan = new Scanner(System.in);

        System.out.println("Make your move!");

        Position pos = new Position();
        for (Axis axis : space.getDimensions()) {

            System.out.println("¿Location for axis '" + AxisNameGenerator.get(axis.getIndex()) + "'?");
            int value = scan.nextInt();
            while (value < 0 || value > axis.getLimit()) {

                System.out.println("The value is less than zero or exceeds axis's limit. Retype it: ");
                value = scan.nextInt();
            }

            pos.addAxis(axis, value);
        }

        //scan.close();

        return pos;
    }

    public static List<Player> askPlayers() {

        scan = new Scanner(System.in);

        List<Player> players = new ArrayList<Player>();

        System.out.println("¿How many players do you want for your game? (Min. 2)");
        int numPlayers = scan.nextInt();
        scan.nextLine();
        while (numPlayers < 2) {

            System.out.println("You can't play with less than 2 players. Retype your awnser: ");
            numPlayers = scan.nextInt();
            scan.nextLine();
        }

        for (int i = 1 ; i <= numPlayers ; i++) {

            System.out.println("Enter a name for player " + i + ": ");
            String name = scan.nextLine();
            while (Utils.nameIsTaken(players, name)) {

                System.out.println("The name choosen for player " + i + " is already taken. Type another: ");
                name = scan.nextLine();
            }

            System.out.println("Enter a char as the user's representative on the game board: ");
            String symbolAux = scan.nextLine();
            char symbol = symbolAux.charAt(0);
            while (Utils.charIsAlreadyUsed(players, symbol)) {

                System.out.println("An existing player is already using that char. Choose another: ");
                symbolAux = scan.nextLine();
                symbol = symbolAux.charAt(0);
            }

            players.add(new Player(name, symbol));
        }

        return players;
    }

    public static int askChainToWin() {

        scan = new Scanner(System.in);

        System.out.println("¿How many positions must be concatenated in order to win?");
        int chainToWin = scan.nextInt();
        while (chainToWin < 3) {

            System.out.println("There must be at least 3 positions lined. Retype your answer: ");
            chainToWin = scan.nextInt();
        }

        return chainToWin;
    }

    public static void printTurnRound(int round, Player player) {

        System.out.println("Round " + (round + 1));
        System.out.println(player.getName() + "'s (" + player.getSymbol() + ") turn: ");
    }

    public static void printStateOfGame(SpaceCursor sCursor, List<Player> players) {

        int prevAxis = 0;
        int val = 0;

        //Given the behaviour of the method that runs through space we're only interested in printing the values for the positions that are generated being the SpaceCursor at the last dimension
        if (sCursor.getCurrentAxis() == sCursor.getSpace().getDimensions().size() - 1) {

            System.out.printf(" %c", Utils.determinePositionSymbol(sCursor.getPosition(), players));

            if (sCursor.isOnInit()) {

                prevAxis = sCursor.getSpace().getAxis(sCursor.getCurrentAxis() - 1).getIndex();
                val = sCursor.getPosition().getCoordinates().get(sCursor.getSpace().getAxis(prevAxis));
                System.out.printf("\t%d\n", val);

                downValuesFlag = true;
                downAxisFlag = -1;
            }
        }
        //The rest of the positions are usefull to divide and categorize the representation within the space
        else {

            if (downAxisFlag != sCursor.getCurrentAxis()) {

                downAxisFlag = sCursor.getCurrentAxis();

                //generateGraphDownValues
                if (downValuesFlag) {

                    downValuesFlag = false;

                    for (int i = sCursor.getSpace().getAxis(sCursor.getCurrentAxis()).getLimit() - 1 ; i >= 0 ; i--) {

                        System.out.printf(" %d", i);
                    }
                    String axisX = AxisNameGenerator.get(sCursor.getSpace().getAxis(sCursor.getCurrentAxis() + 1).getIndex());
                    String axisY = AxisNameGenerator.get(sCursor.getSpace().getAxis(sCursor.getCurrentAxis()).getIndex());
                    System.out.printf(" %s\\%s\n", axisX, axisY);
                }
                
                //Shows the position of the printed section within the rest of dimensions
                if (sCursor.getCurrentAxis() != 0) {

                    prevAxis = sCursor.getSpace().getAxis(sCursor.getCurrentAxis() - 1).getIndex();
                    val = sCursor.getPosition().getCoordinates().get(sCursor.getSpace().getAxis(prevAxis));
                    String code = AxisNameGenerator.get(prevAxis);
                    System.out.println(code + ": " + val + "\n");
                }
            }
        }
    }

    public static void printErrorExistingPosition() {

        System.out.println("The position you've tried to set is already being occupied. Select another position.");
    }

    public static void winningMessage(Player player) {

        System.out.println("Congratulations!!! " + player.getName() + " WON!!");
    }
}