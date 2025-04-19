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
        int dx = Math.abs(this.pos[1] - destX);
        int dy = Math.abs(this.pos[0] - destY);
        return (dx == 0 || dy == 0) && Check.isPathClear(this.pos[0], this.pos[1], destY, destX);
    }
}
