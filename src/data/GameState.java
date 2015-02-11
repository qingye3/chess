package data;

import data.piece.ChessPiece;

import java.util.HashMap;

public class GameState {
    private PlayerSide currentSide;
    HashMap<PlayerSide, HashMap<Position, ChessPiece>> allPieces;
    HashMap<PlayerSide, Boolean> canLongCastle;
    HashMap<PlayerSide, Boolean> canShortCastle;

    public GameState() {
        currentSide = PlayerSide.WHITE;
        canLongCastle = new HashMap<PlayerSide, Boolean>();
        canShortCastle = new HashMap<PlayerSide, Boolean>();
        allPieces = new HashMap<PlayerSide, HashMap<Position, ChessPiece>>();
        for (PlayerSide side : PlayerSide.values()){
            canLongCastle.put(side, true);
            canShortCastle.put(side, true);
            allPieces.put(side, new HashMap<Position, ChessPiece>());
        }
    }

    public PlayerSide getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(PlayerSide currentSide) {
        this.currentSide = currentSide;
    }
}
