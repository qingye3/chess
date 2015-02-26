package chess.application.model;


import chess.application.command.Command;
import chess.application.command.Invoker;
import chess.lib.data.GameState;
import chess.lib.data.GameStateGenerator;
import chess.lib.datatype.GameStatus;
import chess.lib.evaluator.GameStateEvaluator;
import chess.lib.exception.ChessException;

import java.util.Stack;

/**
 * Created by Qing on 2/25/2015.
 * Contains all data needed for the Chess Game
 * Should only create one instance for the application
 */
public class ChessGameModel extends Observable
                            implements Invoker{
    GameState currentState;
    GameStateEvaluator gameStateEvaluator;
    Stack<Command> commandHistory;
    int whiteScore;
    int blackScore;
    boolean isResign;
    boolean isDraw;
    boolean isNoOneTouchAnyThing;

    public boolean isNoOneTouchAnyThing() {
        return isNoOneTouchAnyThing;
    }

    public void setNoOneTouchAnyThing(boolean isNoOneTouchAnyThing) {
        this.isNoOneTouchAnyThing = isNoOneTouchAnyThing;
    }

    public void setResign(boolean isResign) {
        this.isResign = isResign;
    }

    public void setDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    /**
     * Constructor.
     */
    public ChessGameModel() {
        commandHistory = new Stack<Command>();
        gameStateEvaluator = new GameStateEvaluator();
        whiteScore = 0;
        blackScore = 0;
        isResign = false;
        isDraw = false;
        isNoOneTouchAnyThing = true;
        initializeStandardOpening();
    }

    /**
     *
     * @return true if moves can be made
     *         false game is over
     */
    public boolean isPlayable() {
        if (isDraw || isResign){
            return false;
        } else{
            return gameStateEvaluator.evaluate(currentState) == GameStatus.NORMAL;
        }
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    /**
     * Initialize the model with the standard chess opening.
     */
    public void initializeStandardOpening(){
        currentState = GameStateGenerator.standardOpening();
        isResign = false;
        isDraw = false;
        commandHistory.clear();
        notifyObservers();
    }

    /**
     * Initialize the model with the special chess opening
     */
    public void initializeSpecialOpening(){
        isResign = false;
        isDraw = false;
        currentState = GameStateGenerator.specialOpening();
        commandHistory.clear();
        notifyObservers();
    }

    /**
     *
     * @return the current game state
     */
    public GameState getCurrentState(){

        return new GameState(currentState);
    }

    /**
     * setting the game state. Should only be called by commands
     * @param gameState
     */
    public void setGameState(GameState gameState){
        currentState = gameState;
    }

    /**
     * Implements the storeAndExecute function inside the invoker interface
     * Part of the command pattern
     * @param cmd the command to execute
     * @throws ChessException
     */
    @Override
    public void storeAndExecute(Command cmd) throws ChessException {
        cmd.execute();
        notifyObservers();
        commandHistory.push(cmd);
    }

    /**
     * update score by game result
     * Convention in chess tournament
     * +2 if win
     * +1 if draw
     * no change if lose
     * @param result
     */
    public void updateScoreByGameResult(GameStatus result){
        switch (result){
            case NORMAL:
                break;
            case WHITEWINS:
                whiteScore += 2;
                break;
            case BLACKWINS:
                blackScore += 2;
                break;
            case STALEMATE:
                blackScore += 1;
                whiteScore += 1;
        }
    }

    /**
     * Undo a move command
     * Cannot undo a move command that results in a game ending event
     */
    public void undo(){
        if (!isPlayable() || commandHistory.empty()){
            return;
        }
        Command cmd = commandHistory.pop();
        cmd.unexecute();
        notifyObservers();
    }
}
