package DeveloperPackage;

public abstract class Pieces {

    // color and position and symbol of each piece
    protected final String color;
    public int[] pos;
    protected String symbol;
    private boolean hasMoved;
    // create a piece
    public Pieces(String color, int y, int x) {
        this.color = color;
        this.pos = new int[]{y, x};
        this.hasMoved = false;
    }
    //getter for hasMoved
    public boolean hasMoved(){
        return hasMoved;
    }
    //setter for hasMoved

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    //get symbol of piece
    public String getSymbol(){
        return symbol;
    }
    // get color of piece
    public String getColor() {
        return color;
    }

    // check if the move is valid
    public abstract boolean isValidMove(int destY, int destX);
}
