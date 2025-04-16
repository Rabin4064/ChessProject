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
        int startX = this.pos[1];
        int startY = this.pos[0];

        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == dy) && Check.isPathClear(startY, startX, destY, destX);
    }
}
