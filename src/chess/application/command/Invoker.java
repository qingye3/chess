package chess.application.command;

import chess.lib.exception.ChessException;

/**
 * Created by Qing on 2/25/2015.
 * The invoker interface
 * The invoker should keeps a record of commands in order to perform undo
 */
public interface Invoker {
    public void storeAndExecute(Command cmd) throws ChessException;
}
