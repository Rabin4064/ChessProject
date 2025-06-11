package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Pieces {

    public Bishop(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_BISHOP : Symbols.BLACK_BISHOP;
    }

    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == dy) && Check.isPathClear(startY, startX, destY, destX);
    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Diagonals
        addSlidingMoves(moves, directions);
        return moves;
    }
}
