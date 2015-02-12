package unittests.data.piece;

import controller.RookMoveController;
import data.piece.ChessPiece;
import data.piece.Rook;
import datatype.PlayerSide;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {
    private Rook rook;

    @Before
    public void setUp() throws Exception {
        rook = new Rook();
        rook.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(rook.getMoveController() instanceof RookMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("R", rook.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        ChessPiece copy = rook.deepCopy();
        assertEquals("R", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
    }

    @Test
    public void testGetHasMoved() throws Exception{
        assertFalse(rook.isHasMoved());
    }

    @Test
    public void testSetHasMoved() throws Exception{
        rook.setHasMoved(true);
        assertTrue(rook.isHasMoved());
        rook.setHasMoved(false);
        assertFalse(rook.isHasMoved());
    }


}