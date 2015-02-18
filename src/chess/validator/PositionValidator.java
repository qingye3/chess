package chess.validator;

import chess.data.Position;
import chess.exception.ChessBoardException;

/**
 * Created by Qing on 2/11/2015.
 */
public class PositionValidator {
    /**
     * Check if the position is within bound of the chessBoard
     * @param position the position to check
     * @throws ChessBoardException
     */
    public void validate(Position position) throws ChessBoardException {
        if (position.getX() < 0 ||
            position.getX() > 7 ||
            position.getY() < 0 ||
            position.getY()> 7){
            throw new ChessBoardException("Position out of board!");
        }
    }
}
