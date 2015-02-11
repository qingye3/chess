package data.piece;

import controller.MoveController;
import controller.PawnMoveController;

/**
 * Created by Qing on 2/10/2015.
 * This is a class representing a pawn
 */
public class Pawn extends ChessPiece {

    private int firstMovementRound;

    public Pawn() {
        firstMovementRound = -1;
    }

    public Pawn(Pawn other) {
        super(other);
        firstMovementRound = other.firstMovementRound;
    }

    public int getFirstMovementRound() {
        return firstMovementRound;
    }

    public void setFirstMovementRound(int firstMovementRound) {
        this.firstMovementRound = firstMovementRound;
    }

    @Override
    public MoveController getMoveController() {
        return new PawnMoveController();
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Pawn(this);
    }
}
