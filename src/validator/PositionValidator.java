package validator;

import data.Position;
import exception.ChessBoardException;

/**
 * Created by Qing on 2/11/2015.
 */
public class PositionValidator {
    public void validate(Position position) throws ChessBoardException {
        if (position.getX() < 0 ||
            position.getX() > 7 ||
            position.getY() < 0 ||
            position.getY()> 7){
            throw new ChessBoardException("Position out of board!");
        }
    }
}
