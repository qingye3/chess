package chess.data;

import chess.data.piece.ChessPiece;
import chess.datatype.PlayerSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The class to represent the state of the game
 * Store all the information including board configuration etc
 * No behavior is defined within the Class
 */
public class GameState {
    private PlayerSide currentSide;
    private int currentRound;
    private HashMap<String, ChessPiece> allPieces;

    /**
     * Default constructor for a GameState
     * Returns an empty board and set current side to white
     */
    public GameState() {
        currentSide = PlayerSide.WHITE;
        currentRound = 0;
        allPieces = new HashMap<String, ChessPiece>();
    }

    /**
     * Copy constructor
     * @param other
     */
    public GameState(GameState other) {
        currentRound = other.currentRound;
        currentSide = other.currentSide;
        allPieces = new HashMap<String, ChessPiece>();
        for (Map.Entry<String, ChessPiece> entry : other.allPieces.entrySet()){
            String key = entry.getKey();
            ChessPiece value = entry.getValue();
            allPieces.put(key, value.deepCopy());
        }
    }

    /**
     * Simple getter
     * @return current side
     */
    public PlayerSide getCurrentSide() {
        return currentSide;
    }

    /**
     * Simple setter
     * @param currentSide current side
     */
    public void setCurrentSide(PlayerSide currentSide) {
        this.currentSide = currentSide;
    }

    /**
     * Simple getter
     * @return current round
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Simple setter
     * @param currentRound the current round
     */
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    /**
     * Add a chessPiece to the state
     * @param position the position to add the chess Piece
     * @param chessPiece the chess piece to add to the state
     */
    public void addPiece(Position position, ChessPiece chessPiece){
        allPieces.put(position.toString(), chessPiece.deepCopy());
    }

    /**
     * Get a new instance of the chess piece in the indicated position
     * @param position the position of the chess piece
     * @return a new instance of the chess piece copied from the indicated position
     */
    public ChessPiece getPiece(Position position){
        ChessPiece piece = allPieces.get(position.toString());
        if (piece == null) {
            return null;
        }
        else{
            return piece.deepCopy();
        }
    }

    /**
     * remove the chess piece in the indicated position
     * @param position the position of the chess piece
     */
    public void removePiece(Position position){
        allPieces.remove(position.toString());
    }


    /**
     * Used to get the positions of chess pieces belongs to a side
     * @param side the side of the player
     * @return an ArrayList of positions of chess pieces belong to that player
     */
    public ArrayList<Position> getPositions(PlayerSide side){
        ArrayList<Position> ret = new ArrayList<Position>();
        for (Map.Entry<String, ChessPiece> entry : allPieces.entrySet()){
            String key = entry.getKey();
            ChessPiece value = entry.getValue();
            if (value.getPlayerSide() == side){
                ret.add(new Position(key));
            }
        }
        return ret;
    }
}
