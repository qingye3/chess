package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.King;
import chess.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class KingMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    /**
     * Check if the movement of the King is valid
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        King king = (King) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == king.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        //TODO: Castling

        if (!isKingMove(origin, destination)){
            throw new ChessException("Invalid Move: King can only move in one step in any direction");
        }
    }

    private boolean isKingMove(Position origin, Position destination) {
        int xDisplacement = getDisplacementInX(destination, origin);
        int yDisplacement = getDisplacementInY(destination, origin);
        return (xDisplacement >= 0 &&
                xDisplacement <= 1 &&
                yDisplacement >= 0 &&
                yDisplacement <= 1);
    }
}
