package data;

/**
 * Created by Qing on 2/10/2015.
 * This class represents a position on the chess board
 * String representation is algebraic chess notation
 */
public class Position {
    private int x;
    private int y;

    public Position(){
    }

    public Position(Position position){
        this(position.getX(), position.getY());
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(String boardNotation){
        x = boardNotation.charAt(0) - 'a';
        y = Character.getNumericValue(boardNotation.charAt(1)) - 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        char xCode = (char) ('a' +  x);
        String yCode = String.valueOf(y + 1);
        return xCode + yCode;
    }

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
