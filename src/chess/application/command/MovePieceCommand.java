package chess.application.command;


import chess.application.model.ChessGameModel;
import chess.lib.controller.MoveDispatcher;
import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.datatype.GameStatus;
import chess.lib.evaluator.GameStateEvaluator;
import chess.lib.exception.ChessException;


/**
 * Created by Qing on 2/26/2015.
 */
public class MovePieceCommand implements Command{
    ChessGameModel model;
    GameState initialGameState;
    Position origin, destination;
    MoveDispatcher dispatcher;
    public MovePieceCommand(ChessGameModel model, Position origin, Position destination) {
        this.model = model;
        initialGameState = model.getCurrentState();
        dispatcher = new MoveDispatcher();
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void execute() throws ChessException {
        GameState nextState = dispatcher.move(initialGameState, origin, destination);
        model.setNoOneTouchAnyThing(false);
        model.setGameState(nextState);
        GameStateEvaluator evaluator = new GameStateEvaluator();
        GameStatus status = evaluator.evaluate(nextState);
        model.updateScoreByGameResult(status);
    }

    @Override
    public void unexecute() {
        model.setGameState(initialGameState);
    }
}
