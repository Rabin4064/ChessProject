package DeveloperPackage;

import java.util.List;

public abstract class Pieces {

    // color and position and symbol of each piece
    protected final String color;
    public int[] pos;
    protected String symbol;
    public int moveCount;

    public Pieces(String color, int y, int x) {
        this.color = color;
        this.pos = new int[]{y, x};
        this.moveCount = 0;
    }

    public String getSymbol(){
        return symbol;
    }

    public String getColor() {
        return color;
    }

    // primary method to check for valid moves
    public abstract boolean isValidMove(int destY, int destX);

    //get a list of possible moves 
    public abstract List<int[]> getPossibleMoves();


    //helper method for getPossibleMoves.
    protected void addSlidingMoves(List<int[]> moves, int[][] directions) {
        for (int[] dir : directions) {
            int y = this.pos[0] + dir[0];
            int x = this.pos[1] + dir[1];
            while (y >= 0 && y < 8 && x >= 0 && x < 8) {
                Pieces destPiece = Board.getPieceAt(y, x);
                if (destPiece == null) {
                    moves.add(new int[]{y, x});
                } else {
                    if (!destPiece.getColor().equals(this.color)) {
                        moves.add(new int[]{y, x});
                    }
                    break;
                }
                y += dir[0];
                x += dir[1];
            }
        }
    }
}
