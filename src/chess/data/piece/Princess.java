package chess.data.piece;

import chess.controller.MoveController;
import chess.controller.PrincessMoveController;

/**
 * Created by Qing on 2/18/2015.
 */
public class Princess extends ChessPiece{
    public Princess() {
        super();
    }

    public Princess(ChessPiece other) {
        super(other);
    }

    @Override
    public MoveController getMoveController() {
        return new PrincessMoveController();
    }

    @Override
    public String toString() {
        return "NB";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Princess(this);
    }
}
