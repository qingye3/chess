package unittests.controller;

import controller.MoveDispatcher;
import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import data.piece.Rook;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveDispatcherTest {
    MoveDispatcher moveDispatcher;
    @Before
    public void setUp() throws Exception {
        GameState gameState = new GameState();
        gameState.addPiece(new Position(1, 1), new Rook());
        moveDispatcher = new MoveDispatcher();
    }

    //TODO: setup board and make moves
    @Test
    public void testDistpatch() throws Exception {
        assertTrue(true);
    }
}