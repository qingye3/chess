package chess.lib.data.piece;

import chess.lib.controller.MoveController;
import chess.lib.datatype.PlayerSide;


/**
 * Created by Qing on 2/10/2015.
 * This class represents a chess piece.
 */
public abstract class ChessPiece {
    private PlayerSide playerSide;

    /**
     * Default constructor returns a white piece
     * The constructor is to ensure the default behavior of a concrete class is to return a white piece
     */
    public ChessPiece() {
        playerSide = PlayerSide.WHITE;
    }

    /**
     *
     * @param other other chess piece to be copied
     */
    public ChessPiece(ChessPiece other){
        this.playerSide = other.playerSide;
    }

    /**
     * Simple getter
     * @return player side
     */
    public PlayerSide getPlayerSide() {
        return playerSide;
    }

    /**
     * Simple setter
     * @param playerSide player side
     */
    public void setPlayerSide(PlayerSide playerSide) {
        this.playerSide = playerSide;
    }

    /**
     *
     * @return an instance the appropriate concrete MoveController
     */
    public abstract MoveController getMoveController();

    /**
     *
     * @return representation of the ChessPiece in algebraic notation
     */
    public abstract String toString();

    /**
     *
     * @return a new instance of the chessPiece
     */
    public abstract ChessPiece deepCopy();
}
