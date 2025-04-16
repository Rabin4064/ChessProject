package PiecesPackage;

import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class Knight extends Pieces {

    // create knight piece
    public Knight(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_KNIGHT : Symbols.BLACK_KNIGHT;
    }

    // override isValidMove for knight  movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];

        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);
        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);

    }
}
