package chess.data.piece;

import chess.controller.KnightMoveController;
import chess.controller.MoveController;

/**
 * Created by Qing on 2/11/2015.
 * A class representing a Kinght
 */
public class Knight extends ChessPiece {

    /**
     * Constructor
     */
    public Knight() {
        super();
    }

    /**
     * Copy constructor
     * @param other the Knight to copy
     */
    public Knight(ChessPiece other) {
        super(other);
    }

    /**
     *
     * @return an instance of KnightMoveController
     */
    @Override
    public MoveController getMoveController() {
        return new KnightMoveController();
    }

    /**
     *
     * @return "N"
     */
    @Override
    public String toString() {
        return "N";
    }

    /**
     *
     * @return a new instance of the Knight
     */
    @Override
    public ChessPiece deepCopy() {
        return new Knight(this);
    }
}
