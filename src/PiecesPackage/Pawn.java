package PiecesPackage;


import DeveloperPackage.Board;
import DeveloperPackage.Check;
import DeveloperPackage.Pieces;
import DeveloperPackage.Symbols;

public class Pawn extends Pieces {
    private boolean justMovedTwoSquares;
    // create pawn piece
    public Pawn(String color, int y, int x) {
        super(color, y, x);
        this.symbol = color.equals("white") ? Symbols.WHITE_PAWN : Symbols.BLACK_PAWN;
        this.justMovedTwoSquares = false;
    }

    // override isValidMove for pawn movements
    @Override
    public boolean isValidMove(int destY, int destX) {
        int startX = this.pos[1];
        int startY = this.pos[0];
        int forwardDirection = this.getColor().equals("white") ? -1 : 1;

        int dx = destX - startX;
        int dy = destY - startY;

        // standard 1 square move
        if(dx == 0 && dy == forwardDirection){
            return Check.isDestNull(destY, destX);
        }

        // initial 2 square move
        if(this.moveCount == 0 && dx == 0 && dy == 2 * forwardDirection){
            // check if there is a piece blocking the 2 square move
            int middleY = startY + forwardDirection;
            if(Check.isDestNull(middleY, startX) && Check.isDestNull(destY, destX)) {
                this.justMovedTwoSquares = true;
                return true;
            }
            return false;

        }

        // capturing

        if(Math.abs(dx) == 1 && dy == forwardDirection){

            //check if dest is enemy and is not null

            Pieces destPiece = Board.getPieceAt(destY, destX);
            if(destPiece != null){
                return !destPiece.getColor().equals(this.getColor());
            }

            // special En passant logic
            return isValidEnPassant(startY, startX, destY, destX);
        }
        return false;
    }

    private boolean isValidEnPassant(int startY, int startX, int destY, int destX){
        //assigning en passant row
        int enPassantRow = this.getColor().equals("white") ? 3 : 4;
        if(startY != enPassantRow) return false;

        int direction = destX > startX ? 1 : -1;

        Pieces adjacentPiece = Board.getPieceAt(destY, destX + direction);

        return Board.getType(adjacentPiece).equals("Pawn") &&
                !adjacentPiece.getColor().equals(this.color) &&
                ((Pawn)adjacentPiece).justMovedTwoSquares();

    }
    //getter for justMovedTwoSquares

    public boolean justMovedTwoSquares() {
        return this.justMovedTwoSquares;
    }

    public void resetEnPassantFlag(){
        this.justMovedTwoSquares = false;
    }
}
