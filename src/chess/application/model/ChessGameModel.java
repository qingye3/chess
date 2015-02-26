package chess.application.model;


import chess.application.command.Command;
import chess.application.command.Invoker;
import chess.lib.controller.MoveDispatcher;
import chess.lib.data.GameState;
import chess.lib.data.GameStateGenerator;
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
    MoveDispatcher moveDispatcher;
    Stack<Command> commandHistory;

    /**
     * Constructor.
     */
    public ChessGameModel() {
        moveDispatcher = new MoveDispatcher();
        commandHistory = new Stack<Command>();
        initializeStandardOpening();
    }

    /**
     * Initialize the model with the standard chess opening.
     */
    public void initializeStandardOpening(){
        currentState = GameStateGenerator.standardOpening();
    }

    /**
     * Initialize the model with the special chess opening
     */
    public void initializeSpecialOpening(){
        currentState = GameStateGenerator.specialOpening();
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

    @Override
    public void storeAndExecute(Command cmd) throws ChessException {
        cmd.execute();
        notifyObservers();
        commandHistory.push(cmd);
    }

    public void undo(){
        Command cmd = commandHistory.pop();
        cmd.unexecute();
        notifyObservers();
    }
}
