package unittests.controller;

import controller.MoveDispatcher;
import data.GameState;
import data.Position;
import data.piece.King;
import data.piece.Rook;
import datatype.GameStatus;
import datatype.PlayerSide;
import evaluator.GameStateEvaluator;
import exception.ChessException;
import junit.framework.TestCase;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class MoveDispatcherTest extends TestCase {
    GameStateEvaluator evaluator;
    MoveDispatcher dispatcher;
    GameState state;
    @Before
    public void setUp() throws Exception {
        evaluator = new GameStateEvaluator();
        dispatcher = new MoveDispatcher();

        state = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("b2"), new Rook());
        state.addPiece(new Position("b3"), new King());
        state.addPiece(new Position("c6"), new Rook());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("a1"), blackKing);
    }

    @Before
    public void testNormal() throws Exception {
        GameState nextState = dispatcher.move(state, new Position("b2"), new Position("d2"));
        assertEquals(evaluator.evaluate(nextState), GameStatus.NORMAL);
    }

    @Before
    public void testCheckmate() throws Exception {
        GameState nextState = dispatcher.move(state, new Position("c6"), new Position("a6"));
        assertEquals(evaluator.evaluate(nextState), GameStatus.WHITEWINS);
    }

    @Before
    public void testStalemate() throws Exception {
        GameState nextState = dispatcher.move(state, new Position("c6"), new Position("c8"));
        assertEquals(evaluator.evaluate(nextState), GameStatus.STALEMATE);
    }

    @Before
    public void testNoPieceAtOrigin() throws Exception {
        try{
            GameState nextState = dispatcher.move(state, new Position("c3"), new Position("c8"));
            fail("Exception should have been thrown");
        } catch (ChessException e){}
    }

    @Before
    public void testBadMove() throws Exception {
        try{
            GameState nextState = dispatcher.move(state, new Position("c3"), new Position("e5"));
            fail("Exception should have been thrown");
        } catch (ChessException e){}
    }

    @Before
    public void testOutOfBound() throws Exception {
        try{
            GameState nextState = dispatcher.move(state, new Position("c3"), new Position("c9"));
            fail("Exception should have been thrown");
        } catch (ChessException e){}
    }

    @Before
    public void testSuicide() throws Exception {
        try{
            GameState nextState = dispatcher.move(state, new Position("b3"), new Position("a2"));
            fail("Exception should have been thrown");
        } catch (ChessException e){}
    }

    @Before
    public void testMoveOpponent() throws Exception {
        try{
            GameState nextState = dispatcher.move(state, new Position("a1"), new Position("b2"));
            fail("Exception should have been thrown");
        } catch (ChessException e){}
    }
}