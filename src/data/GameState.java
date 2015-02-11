package data;

import data.piece.ChessPiece;
import data.piece.King;
import datatype.PlayerSide;
import exception.ChessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameState {
    private PlayerSide currentSide;
    private int currentRound;
    private HashMap<String, ChessPiece> allPieces;

    public GameState() {
        currentSide = PlayerSide.WHITE;
        currentRound = 0;
        allPieces = new HashMap<String, ChessPiece>();
    }

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

    public PlayerSide getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(PlayerSide currentSide) {
        this.currentSide = currentSide;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void addPiece(Position position, ChessPiece chessPiece){
        allPieces.put(position.toString(), chessPiece.deepCopy());
    }

    public ChessPiece getPiece(Position position){
        ChessPiece piece = allPieces.get(position.toString());
        if (piece == null) {
            return null;
        }
        else{
            return piece.deepCopy();
        }
    }

    public void removePiece(Position position){
        allPieces.remove(position.toString());
    }
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
