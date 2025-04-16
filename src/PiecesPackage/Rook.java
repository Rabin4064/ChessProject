package PiecesPackage;

import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class Rook extends Pieces {


    // create rook piece
    public Rook(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_ROOK : Symbols.BLACK_ROOK;
    }

    // override isValidMove for rook movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == 0 || dy == 0) && Check.isPathClear(startY, startX, destY, destX);
    }
}
