package PiecesPackage;

import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class Bishop extends Pieces {

    // create bishop piece
    public Bishop(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_BISHOP : Symbols.BLACK_BISHOP;
    }

    // override isValidMove for bishop movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int dx = Math.abs(this.pos[1] - destX);
        int dy = Math.abs(this.pos[0] - destY);
        return (dx == dy) && Check.isPathClear(this.pos[0], this.pos[1], destY, destX);
    }
}
