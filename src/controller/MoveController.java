package controller;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import exception.ChessException;
import validator.MoveValidator;

/**
 * Created by Qing on 2/10/2015.
 * This is the "strategy" for the move controller. Implements the "strategy" part of the strategy pattern
 */
public abstract class MoveController {
    protected MoveValidator moveValidator;

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
