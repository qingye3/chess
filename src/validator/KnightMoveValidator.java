package validator;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import data.piece.Knight;
import exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class KnightMoveValidator implements MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Knight knight = (Knight) gameState.getPiece(origin);

        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == knight.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }
        if (!isKnightMove(origin, destination)){
            throw new ChessException("Invalid Move: knight must move two steps forward and one step aside");
        }
    }

    private boolean isKnightMove(Position origin, Position destination) {
        int xDisplacement = abs(origin.getX() - destination.getX());
        int yDisplacement = abs(origin.getY() - destination.getY());
        return (xDisplacement == 2 && yDisplacement == 1) ||
                (xDisplacement == 1 && yDisplacement == 2) ;
    }
}
