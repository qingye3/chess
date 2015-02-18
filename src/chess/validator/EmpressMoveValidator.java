package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.Empress;
import chess.exception.ChessException;

/**
 * Created by Qing on 2/18/2015.
 */
public class EmpressMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    /**
     * Check if the movement of an Empress is valid. Empress can move like a rook or a knight
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Empress empress = (Empress) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == empress.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }
        if (!isKnightMove(origin, destination) && !isInStraightLine(origin, destination)){
            throw new ChessException("Invalid Move: Empress can only move like a knight or a rook");
        }

        if (isInStraightLine(origin, destination) && !isStraightLinePathClear(gameState, origin, destination)){
            throw new ChessException("Invalid Move: Empress cannot jump over another piece when moving like a rook");
        }
    }
}
