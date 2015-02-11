package data.piece;

import controller.KnightMoveController;
import controller.MoveController;

/**
 * Created by Qing on 2/11/2015.
 */
public class Knight extends ChessPiece {
    public Knight() {
    }

    public Knight(ChessPiece other) {
        super(other);
    }

    @Override
    public MoveController getMoveController() {
        return new KnightMoveController();
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Knight(this);
    }
}
