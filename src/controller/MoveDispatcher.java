package controller;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import datatype.GameStatus;
import evaluator.GameStateEvaluator;
import exception.ChessException;
import validator.PositionValidator;

/**
 * Created by Qing on 2/11/2015.
 * A concrete class for dispatching the job
 */
public class MoveDispatcher {
    PositionValidator positionValidator = new PositionValidator();
    GameStateEvaluator gameStateEvaluator = new GameStateEvaluator();

    public GameState move(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(origin);
        ChessPiece piece = gameState.getPiece(origin);
        if (piece == null){
            throw new ChessException ("Chess piece not found at given position");
        }
        if (piece.getPlayerSide() != gameState.getCurrentSide()){
            throw new ChessException ("Cannot move opponent's piece");
        }
        MoveController controller = piece.getMoveController();
        GameState candiateState = controller.move(gameState, origin, destination);
        if(GameStatus.IMPOSSIBLE == gameStateEvaluator.evaluate(candiateState)){
            throw new ChessException ("Invalid Move");
        }
        return candiateState;
    }
}
