package DeveloperPackage;

import java.util.Scanner;

public class Pages {

    // create scanner object for getting entry from user
    static Scanner scanner = new Scanner(System.in);
    // create the board and turn
    private static Board board;
    public static String currentPlayer;

    // first actions for start game
    public Pages() {
        board = new Board();
        currentPlayer = "white";
    }

    // first method for start the game
    public static void firstPage() {
        System.out.println("""
                What do you want to do?
                  0.Help
                  1.SinglePlay
                  2.MultiPlay
                  3.Credits
                  4.Exit Game""");
        System.out.print("--> ");

        // check the entry
        int choice;
        try {
            choice = scanner.nextInt();
            Check.checkChoice(choice);
        } catch (Exception e) {
            // nothing will happen
        }
    }

    // help
    public static void help() {
        System.out.println("""
                to move a piece you have to enter the first and the second place.
                for example, if you want to move the white pawn form a2 to a3 just enter like this: a2 a3""");

        // return to the first page
        System.out.println("-------------------------------------");
        firstPage();
    }

    // change the current player
    public static void switchPlayer() {
        currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    // single play page for gaming with AI
    public static void singlePlayPage() {

    }

    // multiplayer page for gaming with another user
    public static void multiPlayPage() {
        while (true) {
            System.out.println("-------------------------------------");
            // show the board
            board.displayBoard();
            System.out.println(currentPlayer + "'s turn: ");
            System.out.print("--> ");
            // get entries from player
            String start = scanner.next();
            String dest = scanner.next();

            int[] entry1 = transfer(start);
            int[] entry2 = transfer(dest);

            int startY = entry1[0];
            int startX = entry1[1];
            int destY = entry2[0];
            int destX = entry2[1];

            if (Check.isPositionNull(startY, startX)) {
                Errors.nullPositionSelected();
                continue;
            }

            // try to move
            Check.captureOrMove(startY, startX, destY, destX);
        }
    }

    // authors page for see the authors
    public static void authorsPage() {
        System.out.println("Authors:\n\tAhmadreza Ashtar\n\tMobin RangSaz");

        // return to the first page
        System.out.println("-------------------------------------");
        firstPage();
    }

    // exit program page
    public static void exitProgram() {
        System.exit(0);
    }

    private static int[] transfer(String entry) {
        char column = entry.charAt(0);
        char row = entry.charAt(1);

        int c;
        int r;

        c = switch (column) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> -1;
        };

        r = switch (row) {
            case '8' -> 0;
            case '7' -> 1;
            case '6' -> 2;
            case '5' -> 3;
            case '4' -> 4;
            case '3' -> 5;
            case '2' -> 6;
            case '1' -> 7;
            default -> -1;
        };

        return new int[] {r, c};
    }

    // pawn promotion page
    public static int pawnPromotion(String color) {
        if (color.equals("white")) {
            System.out.println("""
                Your soldier has reach the last row!
                which one do you want to convert it to? (enter the number)
                1. Queen ♛
                2. Bishop ♝
                3. Knight ♞
                4. Rook ♜""");
        } else {
            System.out.println("""
                Your soldier has reach the last row!
                which one do you want to convert it to? (enter the number)
                1. Queen ♕
                2. Bishop ♗
                3. Knight ♘
                4. Rook ♖""");
        }
        return scanner.nextInt();
    }

}