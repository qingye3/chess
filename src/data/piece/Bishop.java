package data.piece;

import controller.BishopMoveController;
import controller.MoveController;

/**
 * Created by Qing on 2/11/2015.
 */
public class Bishop extends ChessPiece {
    public Bishop() {
        super();
    }

    public Bishop(ChessPiece other) {
        super(other);
    }

    @Override
    public MoveController getMoveController() {
        return new BishopMoveController();
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Bishop(this);
    }
}
