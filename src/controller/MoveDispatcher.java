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

    /**
     * Returns a state after the move
     * Will throw ChessException if the move is invalid or will expose your King to an attack
     * Returned state is guaranteed to be in one of BLACKWINS, WHITEWINS, STALEMATE, NORMAL
     * @param gameState original game state
     * @param origin which piece to move
     * @param destination move piece to where
     * @return the next state
     * @throws ChessException
     */
    public GameState move(GameState gameState, Position origin, Position destination) throws ChessException {

        positionValidator.validate(origin);
        ChessPiece piece = gameState.getPiece(origin);
        if (piece == null){
            throw new ChessException ("Chess piece not found at given position");
        }
        if (isPieceBelongsToOpponent(gameState, piece)){
            throw new ChessException ("Cannot move opponent's piece");
        }

        //Dispatch the job to a concrete move controller
        MoveController controller = piece.getMoveController();
        GameState candidateState = controller.move(gameState, origin, destination);

        if(isCandidateStateSuicideKing(candidateState)){
            throw new ChessException ("Invalid Move");
        }

        candidateState.setCurrentRound(gameState.getCurrentRound() + 1);
        candidateState.setCurrentSide(gameState.getCurrentSide().getOpponentSide());
        return candidateState;
    }

    private boolean isCandidateStateSuicideKing(GameState candidateState) {
        return GameStatus.IMPOSSIBLE == gameStateEvaluator.evaluate(candidateState);
    }

    private boolean isPieceBelongsToOpponent(GameState gameState, ChessPiece piece) {
        return piece.getPlayerSide() != gameState.getCurrentSide();
    }
}
