package unittests.evaluator;

import data.GameState;
import data.Position;
import data.piece.King;
import data.piece.Queen;
import data.piece.Rook;
import datatype.GameStatus;
import datatype.PlayerSide;
import evaluator.GameStateEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class GameStateEvaluatorTest extends TestCase {
    GameStateEvaluator evaluator;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        evaluator = new GameStateEvaluator();
    }

    @Test
    public void testIsCheckedTrue() throws Exception {
        GameState state = new GameState();

        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("f2"), blackKing);

        Rook blackRook= new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("a3"), blackRook);

        assertTrue(evaluator.isChecked(state, PlayerSide.WHITE));
    }

    @Test
    public void testIsCheckedFalse() throws Exception {
        GameState state = new GameState();

        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("f2"), blackKing);

        assertFalse(evaluator.isChecked(state, PlayerSide.WHITE));
    }

    @Test
    public void testCheckmate() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("c2"), blackKing);

        Queen blackQueen = new Queen();
        blackQueen.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("b2"), blackQueen);

        assertEquals(evaluator.evaluate(state), GameStatus.BLACKWINS);
    }

    @Test
    public void testCheckmate2() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h2"), blackKing);

        Rook blackRook = new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h1"), blackRook);

        Rook blackRook2 = new Rook();
        blackRook2.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("g2"), blackRook2);

        assertEquals(evaluator.evaluate(state), GameStatus.BLACKWINS);
    }

    @Test
    public void testNormal() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("g2"), blackKing);

        Rook blackRook = new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h1"), blackRook);

        Rook blackRook2 = new Rook();
        blackRook2.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h2"), blackRook2);

        assertEquals(evaluator.evaluate(state), GameStatus.NORMAL);
    }

    @Test
    public void testStalemate() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h1"), blackKing);

        Rook blackRook = new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h2"), blackRook);

        Rook blackRook2 = new Rook();
        blackRook2.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("b2"), blackRook2);

        assertEquals(evaluator.evaluate(state), GameStatus.STALEMATE);
    }
}