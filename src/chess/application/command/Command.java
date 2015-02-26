package chess.application.command;
import chess.lib.exception.ChessException;

/**
 * Created by Qing on 2/25/2015.
 * Command interface for the command pattern
 * Constructor must save all the information needed in order to execute the command
 */
public interface Command {
    public void execute() throws ChessException;
    public void unexecute();
    @Override
    public String toString();
}
