package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;
import java.util.ArrayList;
import java.util.List;

public class King extends Pieces {

    public King(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_KING : Symbols.BLACK_KING;
    }

    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);

        if (dx <= 1 && dy <= 1) {
            return true;
        }
        //brilliant castling condition validation
        if (dy == 0 && dx == 2 && this.moveCount == 0 && !Check.isKingInCheck(this.color)) {
            if (destX < startX) {
                Pieces rook = Board.getPieceAt(startY, 0);
                return rook != null && Board.getType(rook).equals("Rook") && rook.moveCount == 0 &&
                        Check.isPathClear(startY, startX, startY, 1);
            }
            if (destX > startX) {
                Pieces rook = Board.getPieceAt(startY, 7);
                return rook != null && Board.getType(rook).equals("Rook") && rook.moveCount == 0 &&
                        Check.isPathClear(startY, startX, startY, 6);
            }
        }
        return false;
    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        int y = this.pos[0];
        int x = this.pos[1];

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newY = y + i;
                int newX = x + j;
                if (newY >= 0 && newY < 8 && newX >= 0 && newX < 8) {
                    moves.add(new int[]{newY, newX});
                }
            }
        }

        if (this.moveCount == 0) {
            moves.add(new int[]{y, x - 2});
            moves.add(new int[]{y, x + 2});
        }
        return moves;
    }
}
