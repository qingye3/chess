package chess.data;

/**
 * Created by Qing on 2/10/2015.
 * This class represents a position on the chess board
 * String representation is algebraic chess notation
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor
     */
    public Position(){
    }

    /**
     * Copy constructor
     * @param position the position to copy
     */
    public Position(Position position){
        this(position.getX(), position.getY());
    }

    /**
     * Constructor
     * @param x the x coordinate on the board (starting with 0)
     * @param y the y coordinate on the board (starting with 0)
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor
     * @param boardNotation the coordinate of the board in algebraic notation
     */
    public Position(String boardNotation){
        x = boardNotation.charAt(0) - 'a';
        y = Character.getNumericValue(boardNotation.charAt(1)) - 1;
    }

    /**
     * simple getter
     * @return the X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * simple setter
     * @param x the X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * simple getter
     * @return y the Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * simple setter
     * @param y the y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return the coordinate in algebraic notation
     */
    @Override
    public String toString() {
        char xCode = (char) ('a' +  x);
        String yCode = String.valueOf(y + 1);
        return xCode + yCode;
    }

    /**
     * Check if two instance of position are equivalent
     * @param obj the other instance of Position
     * @return true if they represent the same position on the board
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null || !(obj instanceof Position)){
            return false;
        }
        else{
            return ((Position) obj).getX() == x && ((Position) obj).getY() == y;
        }
    }
}
