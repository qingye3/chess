package data.piece;

import controller.MoveStrategy;
import datatype.PlayerSide;


/**
 * Created by Qing on 2/10/2015.
 * This class represents a chess piece. Uses the prototype pattern to get deep copy
 */
public abstract class ChessPiece {
    private PlayerSide playerSide;

    public ChessPiece() {
        playerSide = PlayerSide.WHITE;
    }

    public ChessPiece(ChessPiece other){
        this.playerSide = other.playerSide;
    }

    public PlayerSide getPlayerSide() {
        return playerSide;
    }

    public void setPlayerSide(PlayerSide playerSide) {
        this.playerSide = playerSide;
    }

    public abstract MoveStrategy getMoveStrategy();
    public abstract String toString();
    public abstract ChessPiece deepCopy();

}
