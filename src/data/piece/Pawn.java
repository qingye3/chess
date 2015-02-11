package data.piece;

import controller.MoveStrategy;
import controller.PawnMoveStrategy;

/**
 * Created by Qing on 2/10/2015.
 * This is a class representing a pawn
 */
public class Pawn extends ChessPiece {
    public Pawn() {
    }

    public Pawn(ChessPiece other) {
        super(other);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new PawnMoveStrategy();
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
