package unittests.data.piece;

import controller.KnightMoveController;
import data.piece.ChessPiece;
import data.piece.Knight;
import datatype.PlayerSide;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest extends TestCase {
    private ChessPiece knight;

    @Before
    public void setUp() throws Exception {
        knight = new Knight();
        knight.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(knight.getMoveController() instanceof KnightMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("N", knight.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        ChessPiece copy = knight.deepCopy();
        assertEquals("N", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
    }
}