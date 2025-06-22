package DeveloperPackage;

public class Errors {
    // can not move method
    public static void cantMove() {
        System.out.println("Can't move there! Choose another destination.");
    }

    // not this player move
    public static void notYourTurn(){
        System.out.println("It is not your turn to move!");
    }

    // null position has been selected
    public static void nullPositionSelected() {
        System.out.println("You have to choose a piece!");
    }

    // can not do pawn promotion
    public static void cantPromote() {
        System.out.println("You entered an invalid choose!");
    }

    // can not do the action (for undo and redo)
    public static void cantDo() {
        System.out.println("You can't use it!");
    }

}
