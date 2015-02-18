package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.Princess;
import chess.exception.ChessException;

/**
 * Created by Qing on 2/18/2015.
 */
public class PrincessMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Princess princess = (Princess) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == princess.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }
        if (!isKnightMove(origin, destination) && !isInDiagonal(origin, destination)){
            throw new ChessException("Invalid Move: Princess can only move like a bishop or a knight");
        }

        if (isInDiagonal(origin, destination) && !isDiagonalPathClear(gameState, origin, destination)){
            throw new ChessException("Invalid Move: Princess cannot jump over another piece when moving like a bishop");
        }
    }
}
