package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Pieces {

    public Queen(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_QUEEN : Symbols.BLACK_QUEEN;
    }

    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == dy || dx == 0 || dy == 0) && Check.isPathClear(startY, startX, destY, destX);
    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        //combine rook and bishop directions
        int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}, //rook
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}  //bishop
        };
        addSlidingMoves(moves, directions);
        return moves;
    }
}
