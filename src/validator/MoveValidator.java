package validator;

import data.GameState;
import data.Position;
import exception.ChessException;

/**
 * Created by Qing on 2/11/2015.
 */
public interface MoveValidator {
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException;
}
