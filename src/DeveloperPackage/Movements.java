package DeveloperPackage;


public class Movements {

    // move piece
    public static void captureAndMove(int startY, int startX, int destY, int destX) {
        // select the piece that it is in the first place
        Pieces piece = Board.board[startY][startX];

        if (piece.isValidMove(destY, destX)) {
            // move piece and capture destination

            Board.board[destY][destX] = piece;
            Board.board[startY][startX] = null;
            piece.pos[1] = destX;
            piece.pos[0] = destY;
            piece.setHasMoved(true);
            Pages.switchPlayer();

        } else {
            Errors.cantMove();
        }
    }

    // castling
    public static void castling(int startY, int startX, int destY, int destX) {
        Pieces piece = Board.getPieceAt(startY, startX);
        // white castling
        if (piece.getColor().equals("white") && piece.pos[0] == 7 && piece.pos[1] == 4) {
            // left castling
            if (destY == 7 && destX == 2 && Check.isPathClear(piece.pos[0], piece.pos[1], 7, 1)) {
                Movements.moveCastle(piece.pos[0], piece.pos[1], 7, 2);
                Movements.moveCastle(7, 0, 7, 3);
            // right castling
            } if (destY == 7 && destX == 6 && Check.isPathClear(piece.pos[0], piece.pos[1], 7, 6)) {
                Movements.moveCastle(piece.pos[0], piece.pos[1], 7, 6);
                Movements.moveCastle(7, 7, 7, 5);
            }

        // black castling
        } else if (piece.getColor().equals("black") && piece.pos[0] == 0 && piece.pos[1] == 4) {
            // left castling
            if (destY == 0 && destX == 2 && Check.isPathClear(piece.pos[0], piece.pos[1], 0, 1)) {
                Movements.moveCastle(piece.pos[0], piece.pos[1], 0, 2);
                Movements.moveCastle(0, 0, 0, 3);
            // right castling
            } else if (destY == 0 && destX == 6 && Check.isPathClear(piece.pos[0], piece.pos[1], 0, 6)) {
                Movements.moveCastle(piece.pos[0], piece.pos[1], 0, 6);
                Movements.moveCastle(0, 7, 0, 5);
            }
        }
    }

    // castling
    public static void moveCastle(int startY, int startX, int destY, int destX) {
        // select the piece that it is in the first place
        Pieces piece = Board.board[startY][startX];

        // move
        Board.board[destY][destX] = piece;
        Board.board[startY][startX] = null;
        piece.pos[1] = destX;
        piece.pos[0] = destY;
    }
}
