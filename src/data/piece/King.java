package data.piece;

import controller.KingMoveController;
import controller.MoveController;

/**
 * Created by Qing on 2/11/2015.
 */
public class King extends ChessPiece {
    private boolean hasMoved;

    public King() {
        hasMoved = false;
    }

    public King(King other) {
        super(other);
        hasMoved = other.hasMoved;
    }

    @Override
    public MoveController getMoveController() {
        return new KingMoveController();
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public ChessPiece deepCopy() {
        return new King(this);
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
