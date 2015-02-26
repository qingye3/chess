package chess.application.command;

import chess.lib.exception.ChessException;

/**
 * Created by Qing on 2/25/2015.
 */
public interface Invoker {
    public void storeAndExecute(Command cmd) throws ChessException;
}
