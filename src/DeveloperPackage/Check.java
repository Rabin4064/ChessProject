package DeveloperPackage;

public class Check {

    // check the choice that user has been entered
    public static void checkChoice(int choice) {
        switch (choice) {
            case 0 ->
                    Pages.help();
            case 1 -> {
                System.out.println("SinglePlay Mode is running...");
                Pages.singlePlayPage();
            }
            case 2 -> {
                System.out.println("MultiPlay Mode is running...");
                Pages.multiPlayPage();
            }
            case 3 ->
                    Pages.authorsPage();
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

    // find move or capture action
    public static void captureOrMove(int startY, int startX, int destY, int destX) {
        if(!turnCheck(startY, startX)){
            Errors.notYourTurn();
        } else {
            if (Check.canMove(startY, startX, destY, destX)) {
                Movements.captureAndMove(startY, startX, destY, destX);
            } else {
                Errors.cantMove();
            }
        }
    }
    public static boolean turnCheck(int startY, int startX){
        return Board.getPieceAt(startY, startX).getColor().equals(Pages.currentPlayer);
    }

    // check if a piece can move or not
    public static boolean canMove(int startY, int startX, int destY, int destX) {
        if (Board.board[destY][destX] == null) {
            return true;
        } else return !isFriendlyPieceAt(startY, startX, destY, destX);
    }

    //check if the destination is occupied by same color
    public static boolean isFriendlyPieceAt(int startY, int startX, int destY, int destX){
        return Board.board[startY][startX].getColor().equals(Board.board[destY][destX].getColor());
    }

    //check if the destination is null
    public static boolean isDestNull(int destY, int destX){
        return Board.board[destY][destX] == null;
    }

    //check if the path is clear(for sliding pieces)
    public static boolean isPathClear(int startY, int startX, int destY, int destX){
        // steps
        int xStep = Integer.compare(destX, startX);
        int yStep = Integer.compare(destY, startY);

        int x = startX + xStep;
        int y = startY + yStep;

        // the path check step by step
        while(x != destX || y != destY){
            if(Board.getPieceAt(y, x) != null){
                return false;
            }
            x += xStep;
            y += yStep;
        }
        return true;
    }

    // check if the start position is null
    public static boolean isPositionNull(int startY, int startX) {
        return Board.board[startY][startX] == null;
    }
		 
    // Ahh but the time has come for the check and checkmate logic... imma start crying :(
    public static boolean isKingInCheck(String color){
        int[] kingPos = Board.findKingPosition(color);
	    if(kingPos == null) return false;
        int kingRow = kingPos[0];
        int kingCol = kingPos[1];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Pieces p = Board.getPieceAt(i,j);

                if(p != null && !p.getColor().equals(color) && p.isValidMove(kingRow, kingCol)){
                    if(isPathClear(i, j, kingRow, kingCol)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
