package unittests.data.piece;

import chess.controller.BishopMoveController;
import chess.data.piece.Bishop;
import chess.data.piece.ChessPiece;
import chess.datatype.PlayerSide;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BishopTest {
    private ChessPiece bishop;

    @Before
    public void setUp() throws Exception {
        bishop = new Bishop();
        bishop.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(bishop.getMoveController() instanceof BishopMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("B", bishop.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        ChessPiece copy = bishop.deepCopy();
        assertEquals("B", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
    }

}