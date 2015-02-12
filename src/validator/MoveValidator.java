package validator;

import data.GameState;
import data.Position;
import exception.ChessException;

/**
 * Created by Qing on 2/11/2015.
 * Validator interface
 */
public interface MoveValidator {
    /**
     * Check if a move is valid
     * Does not check the origin
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException;
}
