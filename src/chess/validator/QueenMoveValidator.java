package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.Queen;
import chess.exception.ChessBoardException;
import chess.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class QueenMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    /**
     * Check if the movement of a Queen is valid. Throws an exception if it is not. Does not check origin.
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Queen queen = (Queen) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == queen.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        if (!isAligned(origin, destination)){
            throw new ChessException("Invalid Move: Queen can only move in straight line or in diagonal direction");
        }

        if (!isPathClear(gameState, origin, destination)){
            throw new ChessBoardException("Invalid Move: Rook cannot jump over another piece");
        }
    }

    private boolean isAligned(Position origin, Position destination) {
        return isInDiagonal(origin, destination) || isInStraightLine(origin, destination);
    }

    private boolean isPathClear(GameState gameState, Position origin, Position destination) {
        if (isInStraightLine(origin, destination)){
            return isStraightLinePathClear(gameState, origin, destination);
        }
        assert(isInDiagonal(origin, destination));
        return isDiagonalPathClear(gameState, origin, destination);
    }

}
