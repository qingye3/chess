package chess.application.command;

import chess.application.model.ChessGameModel;
import chess.lib.data.Position;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovePieceCommandTest extends TestCase {

    ChessGameModel model;
    Command f4;
    Command e6;
    Command g4;
    Command Qh4;
    @Before
    public void setUp() throws Exception {
        model = new ChessGameModel();
    }

    @Test
    public void testf4e6() throws Exception{
        model.initializeStandardOpening();
        f4 = new MovePieceCommand(model, new Position("f2"), new Position("f4"));
        model.storeAndExecute(f4);
        e6 = new MovePieceCommand(model, new Position("e7"), new Position("e6"));
        model.storeAndExecute(e6);
        assertEquals(model.isPlayable(), true);
    }

    @Test
    public void testCheckMate() throws Exception{
        model.initializeStandardOpening();
        f4 = new MovePieceCommand(model, new Position("f2"), new Position("f4"));
        model.storeAndExecute(f4);
        e6 = new MovePieceCommand(model, new Position("e7"), new Position("e6"));
        model.storeAndExecute(e6);
        g4 = new MovePieceCommand(model, new Position("g2"), new Position("g4"));
        model.storeAndExecute(g4);
        Qh4 = new MovePieceCommand(model, new Position("d8"), new Position("h4"));
        model.storeAndExecute(Qh4);
        assertEquals(model.isPlayable(), false);
    }

    @Test
    public void testUnexecute() throws Exception{
        model.initializeStandardOpening();
        f4 = new MovePieceCommand(model, new Position("f2"), new Position("f4"));
        model.storeAndExecute(f4);
        e6 = new MovePieceCommand(model, new Position("e7"), new Position("e6"));
        model.storeAndExecute(e6);
        g4 = new MovePieceCommand(model, new Position("g2"), new Position("g4"));
        model.storeAndExecute(g4);
        model.undo();
        g4 = new MovePieceCommand(model, new Position("g2"), new Position("g4"));
        model.storeAndExecute(g4);
        Qh4 = new MovePieceCommand(model, new Position("d8"), new Position("h4"));
        model.storeAndExecute(Qh4);
        assertEquals(model.isPlayable(), false);
    }
}