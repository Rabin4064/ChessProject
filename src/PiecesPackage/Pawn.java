package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Pieces {
    private boolean justMovedTwoSquares;

    public Pawn(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_PAWN : Symbols.BLACK_PAWN;
        this.justMovedTwoSquares = false;
    }

    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int forwardDirection = this.getColor().equals("white") ? -1 : 1;
        int dx = destX - startX;
        int dy = destY - startY;

        if (dx == 0 && dy == forwardDirection && Check.isDestNull(destY, destX)) {
            return true;
        }

        if (this.moveCount == 0 && dx == 0 && dy == 2 * forwardDirection) {
            int middleY = startY + forwardDirection;
            return Check.isDestNull(middleY, startX) && Check.isDestNull(destY, destX);
        }

        if (Math.abs(dx) == 1 && dy == forwardDirection) {
            Pieces destPiece = Board.getPieceAt(destY, destX);
            if (destPiece != null && !destPiece.getColor().equals(this.color)) {
                return true;
            }
            return isValidEnPassant(startY, startX, destY, destX);
        }
        return false;
    }
    
    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        int y = this.pos[0];
        int x = this.pos[1];
        int forwardDirection = this.getColor().equals("white") ? -1 : 1;

        int oneForwardY = y + forwardDirection;
        if (oneForwardY >= 0 && oneForwardY < 8 && Board.getPieceAt(oneForwardY, x) == null) {
            moves.add(new int[]{oneForwardY, x});

            if (this.moveCount == 0) {
                int twoForwardY = y + 2 * forwardDirection;
                if (twoForwardY >= 0 && twoForwardY < 8 && Board.getPieceAt(twoForwardY, x) == null) {
                    moves.add(new int[]{twoForwardY, x});
                }
            }
        }

        for (int i = -1; i <= 1; i += 2) {
            int newY = y + forwardDirection;
            int newX = x + i;
            if (newY >= 0 && newY < 8 && newX >= 0 && newX < 8) {
                moves.add(new int[]{newY, newX});
            }
        }
        return moves;
    }

    private boolean isValidEnPassant(int startY, int startX, int destY, int destX) {
        int enPassantRow = this.getColor().equals("white") ? 3 : 4;
        if (startY != enPassantRow) return false;

        Pieces adjacentPiece = Board.getPieceAt(startY, destX);

        if (adjacentPiece == null || !Board.getType(adjacentPiece).equals("Pawn") || adjacentPiece.getColor().equals(this.color)) {
            return false;
        }

        return ((Pawn) adjacentPiece).justMovedTwoSquares(); //brilliant condition handling here
    }

    public boolean justMovedTwoSquares() {
        return this.justMovedTwoSquares;
    }

    public void setJustMovedTwoSquares(boolean value) {
        this.justMovedTwoSquares = value;
    }
}
