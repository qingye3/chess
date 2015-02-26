package chess.lib.validator;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.data.piece.ChessPiece;
import chess.lib.data.piece.Rook;
import chess.lib.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class RookMoveValidator extends MoveValidator{
    private PositionValidator positionValidator = new PositionValidator();
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Rook rook = (Rook) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == rook.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        if (!isInStraightLine(origin, destination)){
            throw new ChessException("Invalid Move: Rook can only move in straight line");
        }
        if (!isStraightLinePathClear(gameState, origin, destination)){
            throw new ChessException("Invalid Move: Rook cannot jump over another piece");
        }
    }
}
