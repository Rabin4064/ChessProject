package PiecesPackage;

import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class Queen extends Pieces {

    // create queen piece
    public Queen(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_QUEEN : Symbols.BLACK_QUEEN;
    }

    // override isValidMove for queen movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int dx = Math.abs(this.pos[1] - destX);
        int dy = Math.abs(this.pos[0] - destY);
        return (dx == dy || dx == 0 || dy == 0) && Check.isPathClear(this.pos[0], this.pos[1], destY, destX);
    }
}
