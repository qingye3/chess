package unittests.data.piece;

import chess.lib.controller.BishopMoveController;
import chess.lib.data.piece.Bishop;
import chess.lib.data.piece.ChessPiece;
import chess.lib.datatype.PlayerSide;
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