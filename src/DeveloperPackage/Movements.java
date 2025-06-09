package DeveloperPackage;

import PiecesPackage.*;

public class Movements {

    public static void executeMove(int startY, int startX, int destY, int destX) {
        Pieces piece = Board.getPieceAt(startY, startX);

        if (piece == null) {
            Errors.nullPositionSelected();
            return;
        }

        if (!Check.turnCheck(startY, startX)) {
            Errors.notYourTurn();
            return;
        }

        if (piece.isValidMove(destY, destX) && Check.canMove(startY, startX, destY, destX)) {
            //special handling for en passant capture
            handleEnPassantCapture(piece, startY, startX, destY, destX);
            
            //handle castling move
            if (Board.getType(piece).equals("King") && Math.abs(destX - startX) == 2) {
                moveCastle(piece, startY, startX, destY, destX);
            } else {
                //regular move
                movePiece(piece, startY, startX, destY, destX);
            }

            //set en passant flag for pawns after a two-square move
            if (Board.getType(piece).equals("Pawn") && Math.abs(destY - startY) == 2) {
                ((Pawn)piece).setJustMovedTwoSquares(true);
            }
            
            //handle pawn promotion
            handlePawnPromotion(piece, destY, destX);
            
            piece.moveCount++;
            Pages.switchPlayer();

            if (Check.isKingInCheck(Pages.currentPlayer)) {
                System.out.println(Pages.currentPlayer + " king is in check");
            }

        } else {
            Errors.cantMove();
        }
    }

    private static void movePiece(Pieces piece, int startY, int startX, int destY, int destX) {
        Board.board[destY][destX] = piece;
        Board.board[startY][startX] = null;
        piece.pos[0] = destY;
        piece.pos[1] = destX;
    }

    private static void moveCastle(Pieces king, int startY, int startX, int destY, int destX) {
        // Move King
        movePiece(king, startY, startX, destY, destX);
        // Move Rook
        if (destX < startX) { //queen side
            Pieces rook = Board.getPieceAt(startY, 0);
            movePiece(rook, startY, 0, startY, 3);
        } else { //king side
            Pieces rook = Board.getPieceAt(startY, 7);
            movePiece(rook, startY, 7, startY, 5);
        }
    }

    private static void handleEnPassantCapture(Pieces piece, int startY, int startX, int destY, int destX) {
        if (Board.getType(piece).equals("Pawn") && Math.abs(destX - startX) == 1 && Board.getPieceAt(destY, destX) == null) {
             //remove the captured pawn
             Board.board[startY][destX] = null;
        }
    }

    private static void handlePawnPromotion(Pieces piece, int destY, int destX) {
        if (!Board.getType(piece).equals("Pawn")) return;

        boolean isWhitePromotion = piece.getColor().equals("white") && destY == 0;
        boolean isBlackPromotion = piece.getColor().equals("black") && destY == 7;

        if (isWhitePromotion || isBlackPromotion) {
            int choice = Pages.pawnPromotion(piece.getColor());
            switch (choice) {
                case 1 -> Board.board[destY][destX] = new Queen(piece.getColor(), destY, destX);
                case 2 -> Board.board[destY][destX] = new Bishop(piece.getColor(), destY, destX);
                case 3 -> Board.board[destY][destX] = new Knight(piece.getColor(), destY, destX);
                case 4 -> Board.board[destY][destX] = new Rook(piece.getColor(), destY, destX);
                default -> {
                    Errors.cantPromotion();
                    //default choice will be the quueen
                    Board.board[destY][destX] = new Queen(piece.getColor(), destY, destX);
                }
            }
        }
    }
}
