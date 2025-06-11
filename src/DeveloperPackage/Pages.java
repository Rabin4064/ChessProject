package DeveloperPackage;

import java.util.Scanner;

public class Pages {
    // for undo and redo
    private static int[] undo = {-1, -1, -1, -1};
    private static boolean redoFlag = false;

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
            // handle non-integer input
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear the invalid input
            firstPage();
        }
    }

    // help
    public static void help() {
        System.out.println("""
                To move a piece you have to enter its current square and the square you want to move it to.
                For example, to move the pawn from e2 to e4, you would type: e2 e4
                also you can use 'undo' and 'redo' by enter it.""");
        System.out.println("-------------------------------------");
        firstPage();
    }

    // change the current player
    public static void switchPlayer() {
        currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    // single play page for gaming with AI
    public static void singlePlayPage() {
        // Future implementation for AI
    }

    //checkmate condition is now handled in multiPlayPage
    public static void multiPlayPage() {
        while (true) {

            System.out.println("-------------------------------------");
            board.displayBoard();
            System.out.println(currentPlayer + "'s turn.");

            //checking for game ending conditions before asking for a move
            if (Check.isCheckmate(currentPlayer)) {
                String winner = currentPlayer.equals("white") ? "Black" : "White";
                System.out.println("CHECKMATE! " + winner + " wins!");
                break; // End game
            }
            if (Check.isStalemate(currentPlayer)) {
                System.out.println("STALEMATE! The game is a draw.");
                break; // End game
            }
             if (Check.isKingInCheck(currentPlayer)) {
                System.out.println(currentPlayer + " is in check!");
            }

            System.out.println("Enter move (e.g., e2 e4):");
            System.out.print("--> ");
            String start = scanner.next();

            // undo
            if (start.equals("undo")) {
                if (!(undo[0]==-1 || undo[1]==-1 || undo[2]==-1 || undo[3]==-1)) {
                    int startY = undo[2];
                    int startX = undo[3];
                    int destY = undo[0];
                    int destX = undo[1];
                    undo = new int[]{startY, startX, destY, destX};
                    Movements.undoAndRedo(startY, startX, destY, destX);
                    redoFlag = true;
                    continue;
                } else {
                    Errors.cantdo();
                    continue;
                }
            // redo
            } else if (start.equals("redo")) {
                if (redoFlag || !(undo[0]==-1 || undo[1]==-1 || undo[2]==-1 || undo[3]==-1)) {
                    int startY = undo[2];
                    int startX = undo[3];
                    int destY = undo[0];
                    int destX = undo[1];
                    undo = new int[]{startY, startX, destY, destX};
                    Movements.undoAndRedo(startY, startX, destY, destX);
                    redoFlag = false;
                    continue;
                }else {
                    Errors.cantdo();
                    continue;
                }
            }

            String dest = scanner.next();

            int[] entry1 = transfer(start);
            int[] entry2 = transfer(dest);

            int startY = entry1[0];
            int startX = entry1[1];
            int destY = entry2[0];
            int destX = entry2[1];

            undo = new int[]{startY, startX, destY, destX};

            //input validation check
            if (startY == -1 || startX == -1 || destY == -1 || destX == -1) {
                System.out.println("Invalid square format. Use format like 'e2 e4'.");
                continue;
            }

            //try to move
            Movements.executeMove(startY, startX, destY, destX);
        }
        //after game ends, return to main menu
        System.out.println("-------------------------------------");
        System.out.println("Returning to main menu...");
        firstPage();
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
        if (entry == null || entry.length() != 2) return new int[]{-1, -1};
        char column = entry.charAt(0);
        char row = entry.charAt(1);
        int c = switch (column) {
            case 'a' -> 0; case 'b' -> 1; case 'c' -> 2; case 'd' -> 3;
            case 'e' -> 4; case 'f' -> 5; case 'g' -> 6; case 'h' -> 7;
            default -> -1;
        };
        int r = switch (row) {
            case '8' -> 0; case '7' -> 1; case '6' -> 2; case '5' -> 3;
            case '4' -> 4; case '3' -> 5; case '2' -> 6; case '1' -> 7;
            default -> -1;
        };
        return new int[]{r, c};
    }

    // pawn promotion page
    public static int pawnPromotion(String color) {
        System.out.println("Your pawn has reached the last row!");
        System.out.println("Which piece do you want to promote to? (Enter the number)");
        if (color.equals("white")) {
            System.out.println("1. Queen ♛  2. Bishop ♝  3. Knight ♞  4. Rook ♜");
        } else {
            System.out.println("1. Queen ♕  2. Bishop ♗  3. Knight ♘  4. Rook ♖");
        }
        System.out.print("--> ");
        return scanner.nextInt();
    }
}
