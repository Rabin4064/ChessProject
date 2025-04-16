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
        // standard 1 square move
        if(dx == 0 && dy == forwardDirection){
            return Check.isDestNull(destY, destX);
        }

        // initial 2 sare move
        if(this.hasMoved == 0 && dx == 0 && dy == 2 * forwardDirection){
            // check if there is a piece blocking the 2 square move
            int middleY = startY + forwardDirection;
            return Check.isDestNull(middleY, startX) && Check.isDestNull(destY, destX);
        }

        // capturing

        if(Math.abs(dx) == 1 && dy == forwardDirection){
            //check if dest is enemy and is not null
            Pieces destPiece = Board.getPieceAt(destY, destX);
            Pieces leftPiece = Board.getPieceAt(destY + forwardDirection, destX -1);
            String lp = leftPiece.toString();
            Pieces rightPiece = Board.getPieceAt(destY + forwardDirection, destX + 1);
            String rp = rightPiece.toString();
            boolean conditionLeft = lp.equals("Pawn") && leftPiece.hasMoved == 1;
            boolean conditionRight = rp.equals("Pawn") && rightPiece.hasMoved == 1;
            if(destPiece == null && leftPiece != null){
                return conditionLeft;
            }
            if(destPiece == null && rightPiece != null){
                return conditionRight;
            }
            if(destPiece != null){
                return !destPiece.getColor().equals(this.getColor());
            }
            return false;
        }
        return false;

    }
}
