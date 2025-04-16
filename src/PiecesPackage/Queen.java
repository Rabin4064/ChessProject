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
        int startX = this.pos[1];
        int startY = this.pos[0];

        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == dy || dx == 0 || dy == 0) && Check.isPathClear(startY, startX, destY, destX);
    }
}
