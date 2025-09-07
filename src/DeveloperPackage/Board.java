package DeveloperPackage;

import PiecesPackage.*;

public class Board {

    // create board for game
    public static Pieces[][] board;
    private static final char[] column = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    // make board [8][8]
    public Board() {
        board = new Pieces[8][8];
        initializeBoard();
    }

    // set first place for pieces
    private void initializeBoard() {
        // black
        // set pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("black", 1, i);
        }
        // set rooks
        board[0][0] = new Rook("black", 0, 0); // left rook
        board[0][7] = new Rook("black", 0, 7); // right rook
        // set knights
        board[0][1] = new Knight("black", 0, 1); // left knight
        board[0][6] = new Knight("black", 0, 6); // right knight
        // set bishops
        board[0][2] = new Bishop("black", 0, 2); // left bishop
        board[0][5] = new Bishop("black", 0, 5); // right bishop
        // set queen
        board[0][3] = new Queen("black", 0, 3);
        // set king
        board[0][4] = new King("black", 0, 4);

        // white
        // set pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("white", 6, i);
        }
        // set rooks
        board[7][0] = new Rook("white", 7, 0); // left rook
        board[7][7] = new Rook("white", 7, 7); // right rook
        // set knights
        board[7][1] = new Knight("white", 7, 1); // left knight
        board[7][6] = new Knight("white", 7, 6); // right knight
        // set bishops
        board[7][2] = new Bishop("white", 7, 2); // left bishop
        board[7][5] = new Bishop("white", 7, 5); // right bishop
        // set queen
        board[7][3] = new Queen("white", 7, 3);
        // set king
        board[7][4] = new King("white", 7, 4);
    }

    // return the place that the piece is at
    public static Pieces getPieceAt(int y, int x) {
        // null position management
        if (y < 0 || y >= 8 || x < 0 || x >= 8) {
            return null;
        }
        return board[y][x];
    }

    // get type of the piece
    public static String getType(Pieces p){
        if(p == null){
            return "null";
        }
        return p.getClass().getSimpleName();
    }

    // show board
    public void displayBoard() {
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(String.format("%2d ", count));
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    if ((i+j)%2==0)
                        System.out.print(" " + Symbols.NULL_BLACK + " ");
                    else
                        System.out.print(" " + Symbols.NULL_WHITE + " ");
                } else {
                    System.out.print(" " + board[i][j].getSymbol() + " ");
                }
            }
            System.out.println();
            count--;
        }
        System.out.print("   ");
        for (char i : column) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

    public static int[] findKingPosition(String color){
		for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieces p = board[i][j];
                if (p != null && getType(p).equals("King") && p.getColor().equals(color)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
