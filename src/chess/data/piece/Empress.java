package chess.data.piece;

import chess.controller.EmpressMoveController;
import chess.controller.MoveController;

/**
 * Created by Qing on 2/18/2015.
 * A class to represent an Empress.
 * Empress is a chess piece that moves like a rook or a knight
 */
public class Empress extends ChessPiece {
    public Empress() {
        super();
    }

    public Empress(ChessPiece other) {
        super(other);
    }

    @Override
    public MoveController getMoveController() {
        return new EmpressMoveController();
    }

    @Override
    public String toString() {
        return "RN";
    }

    @Override
    public ChessPiece deepCopy() {
        return new Empress(this);
    }
}
