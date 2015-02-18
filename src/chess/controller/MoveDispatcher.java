package chess.controller;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.datatype.GameStatus;
import chess.evaluator.GameStateEvaluator;
import chess.exception.ChessException;
import chess.validator.PositionValidator;

/**
 * Created by Qing on 2/11/2015.
 * The class that dispatch a move to a specific controller
 * Will validate if a move is valid
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

        //Dispatch the job to a concrete move chess.controller
        MoveController controller = piece.getMoveController();
        GameState candidateState = controller.move(gameState, origin, destination);

        if(isCandidateStateSuicideKing(candidateState)){
            throw new ChessException ("Invalid Move");
        }

        candidateState.setCurrentRound(gameState.getCurrentRound() + 1);
        candidateState.setCurrentSide(gameState.getCurrentSide().getOpponentSide());
        return candidateState;
    }

    /**
     * Check if a state is the result of a move that expose King to the opponent
     * @param candidateState The state to be checked
     * @return true if King is exposed to the opponent
     */
    private boolean isCandidateStateSuicideKing(GameState candidateState) {
        return GameStatus.IMPOSSIBLE == gameStateEvaluator.evaluate(candidateState);
    }

    /**
     * Check if a chess piece belongs to the opponent of the current side
     * @param gameState The current game state
     * @param piece The piece to be checked
     * @return True if the piece belongs to the opponent of the current side
     */
    private boolean isPieceBelongsToOpponent(GameState gameState, ChessPiece piece) {
        return piece.getPlayerSide() != gameState.getCurrentSide();
    }
}
