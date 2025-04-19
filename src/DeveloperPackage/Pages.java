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
                  0. Get Help About this game
                  1.SinglePlay
                  2.MultiPlay
                  3.See Authors
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
                to move:
                  in order to move a piece, enter the current position of the piece,
                  following by the destination position.
                  tip:
                    the squares start from the index[0][0] and end at index[8][8].
                  for example:
                    if the white player wants to move his pawn from a2 to a3
                    he has to imagine an 8 by 8 matrix and enter: 6 0 5 0 (from [6][0] to [5][0])
                board:
                  imagine the board like this:
                    a8 b8 c8 d8 e8 f8 g8 h8     -->     [0][0] [0][1] [0][2] [0][3] [0][4] [0][5] [0][6] [0][7]
                    a7 b7 c7 d7 e7 f7 g7 h7     -->     [1][0] [1][1] [1][2] [1][3] [1][4] [1][5] [1][6] [1][7]
                    a6 b6 c6 d6 e6 f6 g6 h6     -->     [2][0] [2][1] [2][2] [2][3] [2][4] [2][5] [2][6] [2][7]
                    a5 b5 c5 d5 e5 f5 g5 h5     -->     [3][0] [3][1] [3][2] [3][3] [3][4] [3][5] [3][6] [3][7]
                    a4 b4 c4 d4 e4 f4 g4 h4     -->     [4][0] [4][1] [4][2] [4][3] [4][4] [4][5] [4][6] [4][7]
                    a3 b3 c3 d3 e3 f3 g3 h3     -->     [5][0] [5][1] [5][2] [5][3] [5][4] [5][5] [5][6] [5][7]
                    a2 b2 c2 d2 e2 f2 g2 h2     -->     [6][0] [6][1] [6][2] [6][3] [6][4] [6][5] [6][6] [6][7]
                    a1 b1 c1 d1 e1 f1 g1 h1     -->     [7][0] [7][1] [7][2] [7][3] [7][4] [7][5] [7][6] [7][7]""");

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
//            Check.pawnPromotionCheck();

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
        System.out.println("Authors:\n\tAhmadReza Ashtar\n\tMobin RangSaz");

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

        if (column == 'a') c = 0;
        else if (column == 'b') c = 1;
        else if (column == 'c') c = 2;
        else if (column == 'd') c = 3;
        else if (column == 'e') c = 4;
        else if (column == 'f') c = 5;
        else if (column == 'g') c = 6;
        else c = 7;

        if (row == '8') r = 0;
        else if (row == '7') r = 1;
        else if (row == '6') r = 2;
        else if (row == '5') r = 3;
        else if (row == '4') r = 4;
        else if (row == '3') r = 5;
        else if (row == '2') r = 6;
        else r = 7;

        return new int[] {r, c};
        
        
    }

//    public static void pawnPromotion() {
//        System.out.print("""
//                Your Soldier has reached last row!
//                Choose your HighRanked piece that you want to convert it to:
//                1. Queen ♛
//                2. Bishop ♝
//                3. Knight ♞
//                4. Rook ♜
//                Enter the number of your choice:
//                -->\s""");
//        Check.checkInputForPawnPromotion(scanner.nextInt());
//
//    }
}