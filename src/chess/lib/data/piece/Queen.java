package chess.lib.data.piece;

import chess.lib.controller.MoveController;
import chess.lib.controller.QueenMoveController;

/**
 * Created by Qing on 2/11/2015.
 * A class representing a Queen
 */
public class Queen extends ChessPiece {
    /**
     * Constructor
     */
    public Queen() {
        super();
    }

    /**
     * Copy Constructor
     * @param other the Queen to copy
     */
    public Queen(ChessPiece other) {
        super(other);
    }

    /**
     *
     * @return an instance of QueenMoveController
     */
    @Override
    public MoveController getMoveController() {
        return new QueenMoveController();
    }

    /**
     *
     * @return "Q"
     */
    @Override
    public String toString() {
        return "Q";
    }

    /**
     *
     * @return a deep copy of the Queen
     */
    @Override
    public ChessPiece deepCopy() {
        return new Queen(this);
    }
}
