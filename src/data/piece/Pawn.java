package data.piece;

import controller.MoveController;
import controller.PawnMoveController;

/**
 * Created by Qing on 2/10/2015.
 */
public class Pawn extends ChessPiece {
    @Override
    public MoveController getMoveController() {
        return new PawnMoveController();
    }

    @Override
    public String toString() {
        return "";
    }
}
