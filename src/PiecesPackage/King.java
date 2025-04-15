package PiecesPackage;

import DeveloperPackage.Check;
import DeveloperPackage.Movements;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class King extends Pieces {

    // create king piece
    public King(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_KING : Symbols.BLACK_KING;
    }

    // override isValidMove for king movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];

        int dx = Math.abs(startX - destX);
        int dy = Math.abs(startY - destY);


//        if(dx <= 1 && dy <= 1)
        // white castling
        if (this.getColor().equals("white") && this.pos[0] == 7 && this.pos[1] == 4 && dy == 0 && dx == 2) {
            // left castling
            if (Check.isPathClear(this.pos[0], this.pos[1], 7, 1)) {
                Movements.castling(this.pos[0], this.pos[1], 7, 2);
                return false;
            // right castling
            } else if (Check.isPathClear(this.pos[0], this.pos[1], 7, 6)) {
                Movements.castling(this.pos[0], this.pos[1], 7, 6);
                return false;
            }
        // black castling
        } else if (this.getColor().equals("black") && this.pos[0] == 0 && this.pos[1] == 4 && dy == 0 && dx == 2) {
            // left castling
            if (destY == 0 && destX == 2 && Check.isPathClear(this.pos[0], this.pos[1], 0, 1)) {
                Movements.castling(this.pos[0], this.pos[1], 0, 2);
                return false;
            // right castling
            } else if (Check.isPathClear(this.pos[0], this.pos[1], 0, 6)) {
                Movements.castling(this.pos[0], this.pos[1], 0, 6);
                return false;
            }
        }
        // regular move
        return dx <= 1 && dy <= 1;
    }
}
