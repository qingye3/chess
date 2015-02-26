package chess.application.command;


import chess.application.model.ChessGameModel;
import chess.lib.controller.MoveDispatcher;
import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.datatype.PlayerSide;
import chess.lib.exception.ChessException;
import javafx.geometry.Pos;

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
        System.out.println(initialGameState.getPositions(PlayerSide.WHITE));
        System.out.println(origin);
        System.out.println(destination);
        dispatcher = new MoveDispatcher();
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void execute() throws ChessException {
        GameState nextState = dispatcher.move(initialGameState, origin, destination);
        model.setGameState(nextState);
    }

    @Override
    public void unexecute() {
        model.setGameState(initialGameState);
    }
}
