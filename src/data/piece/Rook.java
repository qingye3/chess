package data.piece;

import controller.MoveController;
import controller.PawnMoveController;
import controller.RookMoveController;

/**
 * Created by Qing on 2/10/2015.
 * This is a class representing a pawn
 */
public class Rook extends ChessPiece {
    private boolean hasMoved;

    public Rook() {
        hasMoved = false;
    }

    public Rook(Rook other) {
        super(other);
        hasMoved = other.hasMoved;
    }

    @Override
    public MoveController getMoveController() {
        return new RookMoveController();
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Rook(this);
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
