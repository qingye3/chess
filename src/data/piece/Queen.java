package data.piece;

import controller.MoveController;
import controller.QueenMoveController;

/**
 * Created by Qing on 2/11/2015.
 */
public class Queen extends ChessPiece {
    public Queen() {
    }

    public Queen(ChessPiece other) {
        super(other);
    }

    @Override
    public MoveController getMoveController() {
        return new QueenMoveController();
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Queen(this);
    }
}
