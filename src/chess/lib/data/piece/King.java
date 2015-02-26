package chess.lib.data.piece;

import chess.lib.controller.KingMoveController;
import chess.lib.controller.MoveController;

/**
 * Created by Qing on 2/11/2015.
 * A class representing a King
 */
public class King extends ChessPiece {
    private boolean hasMoved;

    /**
     * Constructor
     */
    public King() {
        super();
        hasMoved = false;
    }

    /**
     * Copy constructor
     * @param other the King to copy
     */
    public King(King other) {
        super(other);
        hasMoved = other.hasMoved;
    }

    /**
     *
     * @return the an instance of KingMoveController
     */
    @Override
    public MoveController getMoveController() {
        return new KingMoveController();
    }

    /**
     *
     * @return "K"
     */
    @Override
    public String toString() {
        return "K";
    }

    /**
     *
     * @return a deep copy of the King
     */
    @Override
    public ChessPiece deepCopy() {
        return new King(this);
    }

    /**
     * Simple getter
     * @return if the King has moved
     */
    public boolean isHasMoved() {
        return hasMoved;
    }

    /**
     * Simple setter
     * @param hasMoved if the King has moved
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
