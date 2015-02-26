package unittests.data.piece;

import chess.lib.controller.PrincessMoveController;
import chess.lib.data.piece.Princess;
import chess.lib.datatype.PlayerSide;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


public class PrincessTest extends TestCase{

    private Princess princess;

    @Before
    public void setUp() throws Exception {
        princess = new Princess();
        princess.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(princess.getMoveController() instanceof PrincessMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("NB", princess.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        Princess copy = (Princess) princess.deepCopy();
        assertEquals("NB", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
    }

}