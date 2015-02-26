package chess.lib.controller;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.data.piece.ChessPiece;
import chess.lib.exception.ChessException;
import chess.lib.validator.MoveValidator;

/**
 * Created by Qing on 2/10/2015.
 * Abstract controller with default move protocol
 * See the move public function for details
 */
public abstract class MoveController {
    protected MoveValidator moveValidator;

    /**
     * Default move behavior
     * Will check for invalid move but does not check if origin is valid
     * Throws ChessException when invalid moves occur
     *
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @return a state after the move
     * @throws ChessException
     */
    public GameState move(GameState gameState, Position origin, Position destination) throws ChessException{
        moveValidator.validate(gameState, origin, destination);

        GameState retState = new GameState(gameState);
        retState.setCurrentRound(retState.getCurrentRound() + 1);

        retState.removePiece(destination);
        ChessPiece piece= retState.getPiece(origin);
        retState.removePiece(destination);
        retState.addPiece(destination, piece);
        retState.removePiece(origin);

        retState.setCurrentSide(retState.getCurrentSide().getOpponentSide());

        return retState;
    }
}
