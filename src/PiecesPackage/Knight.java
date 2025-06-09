package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Pieces {

    public Knight(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_KNIGHT : Symbols.BLACK_KNIGHT;
    }

    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < 8; i++) {
            int newY = this.pos[0] + dy[i];
            int newX = this.pos[1] + dx[i];

            if (newY >= 0 && newY < 8 && newX >= 0 && newX < 8) {
                moves.add(new int[]{newY, newX});
            }
        }
        return moves;
    }
}
