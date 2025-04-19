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

//    public static void checkInputForPawnPromotion(int choice) {
//        switch (choice){
//            case 1 -> ;
//            case 2 -> ;
//            case 3 -> ;
//            case 4 -> ;
//            default -> {
//                System.out.println("Invalid choice...");
//                Pages.pawnPromotion();
//            }
//        }
//    }

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
        if (Board.board[startY][startX] == null) {
            return true;
        }
        return false;
    }

    // pawn promotion
//    public static void pawnPromotionCheck() {
//        if () {
//            Pages.pawnPromotion();
//        }
//    }
}
