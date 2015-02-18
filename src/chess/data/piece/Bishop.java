package chess.data.piece;

import chess.controller.BishopMoveController;
import chess.controller.MoveController;

/**
 * Created by Qing on 2/11/2015.
 * A class representing a Bishop
 */
public class Bishop extends ChessPiece {
    /**
     * Constructor
     */
    public Bishop() {
        super();
    }

    /**
     * Copy constructor
     * @param other The piece to copy
     */
    public Bishop(ChessPiece other) {
        super(other);
    }

    /**
     *
     * @return an instance of BishopMoveController
     */
    @Override
    public MoveController getMoveController() {
        return new BishopMoveController();
    }

    @Override
    public String toString() {
        return "B";
    }

    /**
     *
     * @return A deep copied instance of Bishop.
     */
    @Override
    public ChessPiece deepCopy() {
        return new Bishop(this);
    }
}
