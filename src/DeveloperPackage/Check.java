package DeveloperPackage;

import java.util.List;

public class Check {
    // hasLegalMoves had to check for valid moves with 4 nested for loops(yes it had to be like that)
    // in order to have a simple logic, so I came up with this logic but we have to get a list of 
    // possibleMoves; this version is a lot more optimized.
    private static boolean hasLegalMoves(String color) {
        for (int startY = 0; startY < 8; startY++) {
            for (int startX = 0; startX < 8; startX++) {
                Pieces piece = Board.getPieceAt(startY, startX);
                if (piece != null && piece.getColor().equals(color)) {
                    //get list of potential moves for this piece.
                    List<int[]> possibleMoves = piece.getPossibleMoves();

                    for (int[] move : possibleMoves) {
                        int destY = move[0];
                        int destX = move[1];

                        //check for validation before simulation
                        if (!canMove(startY, startX, destY, destX)) {
                            continue;
                        }

                        //simulate the move
                        Pieces capturedPiece = Board.getPieceAt(destY, destX);
                        Board.board[destY][destX] = piece;
                        Board.board[startY][startX] = null;
                        int[] originalPos = piece.pos;
                        piece.pos = new int[]{destY, destX};

                        if (!isKingInCheck(color)) {
                            //undo move and return true if a legal move was found
                            Board.board[startY][startX] = piece;
                            Board.board[destY][destX] = capturedPiece;
                            piece.pos = originalPos;
                            return true;
                        }

                        //undo move
                        Board.board[startY][startX] = piece;
                        Board.board[destY][destX] = capturedPiece;
                        piece.pos = originalPos;
                    }
                }
            }
        }
        return false; //no legal moves found
    }

    
    public static void checkChoice(int choice) {
        switch (choice) {
            case 0 -> Pages.help();
            case 1 -> {
                System.out.println("SinglePlay Mode is not yet implemented...");
                Pages.firstPage();
            }
            case 2 -> {
                System.out.println("MultiPlay Mode is running...");
                Pages.multiPlayPage();
            }
            case 3 -> Pages.authorsPage();
            case 4 -> {
                System.out.println("Exiting program...");
                Pages.exitProgram();
            }
            default -> {
                System.out.println("Invalid choice...");
                Pages.firstPage();
            }
        }
    }

    public static void captureOrMove(int startY, int startX, int destY, int destX) {
        Movements.executeMove(startY, startX, destY, destX);
    }

    public static boolean turnCheck(int startY, int startX) {
        Pieces piece = Board.getPieceAt(startY, startX);
        return piece != null && piece.getColor().equals(Pages.currentPlayer);
    }

    public static boolean canMove(int startY, int startX, int destY, int destX) {
        Pieces destPiece = Board.getPieceAt(destY, destX);
        if (destPiece == null) {
            return true;
        } else {
            return !isFriendlyPieceAt(startY, startX, destY, destX);
        }
    }

    public static boolean isFriendlyPieceAt(int startY, int startX, int destY, int destX) {
        return Board.getPieceAt(startY, startX).getColor().equals(Board.getPieceAt(destY, destX).getColor());
    }

    public static boolean isDestNull(int destY, int destX) {
        return Board.getPieceAt(destY, destX) == null;
    }

    public static boolean isPathClear(int startY, int startX, int destY, int destX) {
        int xStep = Integer.compare(destX, startX);
        int yStep = Integer.compare(destY, startY);
        int x = startX + xStep;
        int y = startY + yStep;
        while (x != destX || y != destY) {
            if (Board.getPieceAt(y, x) != null) {
                return false;
            }
            x += xStep;
            y += yStep;
        }
        return true;
    }
    
    public static boolean isPositionNull(int startY, int startX) {
        return Board.getPieceAt(startY, startX) == null;
    }
    
    public static boolean isKingInCheck(String color) {
        int[] kingPos = Board.findKingPosition(color);
        if (kingPos == null) return false;
        int kingRow = kingPos[0];
        int kingCol = kingPos[1];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieces p = Board.getPieceAt(i, j);
                if (p != null && !p.getColor().equals(color) && p.isValidMove(kingRow, kingCol)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isCheckmate(String color) {
        return isKingInCheck(color) && !hasLegalMoves(color);
    }
    
    public static boolean isStalemate(String color) {
        return !isKingInCheck(color) && !hasLegalMoves(color);
    }
}
