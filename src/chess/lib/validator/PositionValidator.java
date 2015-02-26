package chess.lib.validator;

import chess.lib.constants.Constants;
import chess.lib.data.Position;
import chess.lib.exception.ChessBoardException;

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
            position.getX() >= Constants.BOARDWIDTH||
            position.getY() < 0 ||
            position.getY() >= Constants.BOARDHEIGHT){
            throw new ChessBoardException("Position out of board!");
        }
    }
}
