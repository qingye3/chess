package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.Knight;
import chess.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class KnightMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    /**
     * Check if th movement if the Knight is valid. Throws an exception if it is not
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
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

}
