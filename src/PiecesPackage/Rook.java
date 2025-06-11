package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Pieces {

    public Rook(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_ROOK : Symbols.BLACK_ROOK;
    }

    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == 0 || dy == 0) && Check.isPathClear(startY, startX, destY, destX);
    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // Right, Left, Down, Up
        addSlidingMoves(moves, directions);
        return moves;
    }
}
