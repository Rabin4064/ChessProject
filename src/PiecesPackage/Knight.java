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
        int dx = Math.abs(this.pos[1] - destX);
        int dy = Math.abs(this.pos[0] - destY);
        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);

    }
}
