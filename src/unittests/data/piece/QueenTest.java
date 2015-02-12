package unittests.data.piece;

import controller.QueenMoveController;
import data.piece.ChessPiece;
import data.piece.Queen;
import datatype.PlayerSide;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueenTest {
    private ChessPiece queen;

    @Before
    public void setUp() throws Exception {
        queen = new Queen();
        queen.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(queen.getMoveController() instanceof QueenMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Q", queen.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        ChessPiece copy = queen.deepCopy();
        assertEquals("Q", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
    }
}