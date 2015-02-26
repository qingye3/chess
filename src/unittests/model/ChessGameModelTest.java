package unittests.model;

import chess.application.model.ChessGameModel;
import chess.lib.data.GameState;
import chess.lib.data.Position;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessGameModelTest extends TestCase {
    ChessGameModel chessGameModel;

    @Before
    public void setUp() throws Exception {
        chessGameModel = new ChessGameModel();
    }

    @Test
    public void testGetters() throws Exception{
        assertEquals(chessGameModel.getWhiteScore(), 0);
        assertEquals(chessGameModel.getBlackScore(), 0);
        GameState gameState = chessGameModel.getCurrentState();
        assertEquals(gameState.getPiece(new Position("b1")).toString(), "N");
        assertEquals(gameState.getPiece(new Position("f1")).toString(), "B");
        assertEquals(chessGameModel.isPlayable(), true);
        assertEquals(chessGameModel.isNoOneTouchAnyThing(), true);
    }

    @Test
    public void testSpecialOpening() throws Exception{
        chessGameModel.initializeSpecialOpening();
        GameState gameState = chessGameModel.getCurrentState();
        assertEquals(gameState.getPiece(new Position("a1")).toString(), "RN");
        assertEquals(gameState.getPiece(new Position("f1")).toString(), "NB");
        assertEquals(chessGameModel.isPlayable(), true);
    }

    @Test
    public void testSetters() throws Exception{
        chessGameModel.setResign(true);
        assertEquals(chessGameModel.isPlayable(), false);
        chessGameModel.setResign(false);
        chessGameModel.setDraw(true);
        assertEquals(chessGameModel.isPlayable(), false);
        chessGameModel.setNoOneTouchAnyThing(false);
        assertEquals(chessGameModel.isNoOneTouchAnyThing(), false);
    }
}