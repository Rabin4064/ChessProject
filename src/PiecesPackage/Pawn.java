package PiecesPackage;

import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class Pawn extends Pieces {

    // create pawn piece
    public Pawn(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_PAWN : Symbols.BLACK_PAWN;
    }

    // override isValidMove for pawn movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int forwardDirection;
        int startingRow;

        int dx = destX - startX;
        int dy = destY - startY;

        if(this.getColor().equals("white")){
            forwardDirection = -1;
            startingRow = 6; // may use later
        }
        else{
            forwardDirection = 1;
            startingRow = 1; // may be used later
        }
        // standard 1 sqaure move
        if(dx == 0 && dy == forwardDirection){
            return Check.isDestNull(destY, destX);
        }

        // initial 2 sare move
        if(!this.hasMoved() && dx == 0 && dy == 2 * forwardDirection){
            // check if there is a piece blocking the 2 sqaure move
            int middleY = startY + forwardDirection;
            return Check.isDestNull(middleY, startX) && Check.isDestNull(destY, destX);
        }

        // capturing
        if(Math.abs(dx) == 1 && dy == forwardDirection){
            //check if dest is enemy and is not null
            Pieces destPiece = Board.getPieceAt(destY, destX);
            if(destPiece != null){
                return !destPiece.getColor().equals(this.getColor());
            }
            return false;
        }
        return false;

    }
}
