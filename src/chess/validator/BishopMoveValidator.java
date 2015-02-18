package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.Bishop;
import chess.data.piece.ChessPiece;
import chess.exception.ChessBoardException;
import chess.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 * Validator for Bishop
 */
public class BishopMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    /**
     * Check if a move of Bishop is valid. Throw an exception when the move is invalid. Does not check origin.
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Bishop bishop= (Bishop) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == bishop.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        if (!isInDiagonal(origin, destination)){
            throw new ChessException("Invalid Move: Bishop can only move in diagonal direction");
        }

        if (!isDiagonalPathClear(gameState, origin, destination)){
            throw new ChessBoardException("Invalid Move: Bishop cannot jump over another piece");
        }
    }
}
