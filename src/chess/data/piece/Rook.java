package chess.data.piece;

import chess.controller.MoveController;
import chess.controller.RookMoveController;

/**
 * Created by Qing on 2/10/2015.
 * A class representing a Rook
 */
public class Rook extends ChessPiece {
    private boolean hasMoved;

    /**
     * Constructor
     */
    public Rook() {
        super();
        hasMoved = false;
    }

    /**
     * Copy constructor
     * @param other the Rook to copy
     */
    public Rook(Rook other) {
        super(other);
        hasMoved = other.hasMoved;
    }

    /**
     *
     * @return an instance of RookMoveController
     */
    @Override
    public MoveController getMoveController() {
        return new RookMoveController();
    }

    /**
     *
     * @return "R"
     */
    @Override
    public String toString() {
        return "R";
    }

    /**
     *
     * @return a deep copy of the Rook
     */
    @Override
    public ChessPiece deepCopy() {
        return new Rook(this);
    }

    /**
     * Simple getter
     * @return if the Rook has moved
     */
    public boolean isHasMoved() {
        return hasMoved;
    }

    /**
     * Simple setter
     * @param hasMoved if the Rook has moved
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
