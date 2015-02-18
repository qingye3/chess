package chess.data.piece;

import chess.controller.MoveController;
import chess.controller.PawnMoveController;

/**
 * Created by Qing on 2/10/2015.
 * A class representing a pawn
 */
public class Pawn extends ChessPiece {

    private int firstMovementRound;

    /**
     * Constructor
     */
    public Pawn() {
        super();
        firstMovementRound = -1;
    }

    /**
     *
     * @param other the piece to copy
     */
    public Pawn(Pawn other) {
        super(other);
        firstMovementRound = other.firstMovementRound;
    }

    /**
     *
     * @return when the Pawn was first moved. Useful for implementing en passant
     */
    public int getFirstMovementRound() {
        return firstMovementRound;
    }

    /**
     *
     * @param firstMovementRound when the Pawn was first moved.
     */
    public void setFirstMovementRound(int firstMovementRound) {
        this.firstMovementRound = firstMovementRound;
    }

    /**
     *
     * @return an instance of PawnMoveController
     */
    @Override
    public MoveController getMoveController() {
        return new PawnMoveController();
    }

    /**
     *
     * @return empty string
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     *
     * @return a new instance of the Pawn
     */
    @Override
    public ChessPiece deepCopy() {
        return new Pawn(this);
    }
}
