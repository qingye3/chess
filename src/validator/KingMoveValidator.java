package validator;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import data.piece.King;
import exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class KingMoveValidator implements MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

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
        int xDisplacement = abs(origin.getX() - destination.getX());
        int yDisplacement = abs(origin.getY() - destination.getY());
        return (xDisplacement >= 0 &&
                xDisplacement <= 1 &&
                yDisplacement >= 0 &&
                yDisplacement <= 1);
    }
}
