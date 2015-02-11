package unittests.data;

import controller.PawnMoveStrategy;
import data.piece.ChessPiece;
import data.piece.Pawn;
import datatype.PlayerSide;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessPiece pawn;

    @Before
    public void setUp() throws Exception {
        pawn = new Pawn();
        pawn.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(pawn.getMoveStrategy() instanceof PawnMoveStrategy);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("", pawn.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        ChessPiece copy = pawn.deepCopy();
        assertEquals("", copy.toString());
        assertEquals(PlayerSide.BLACK, pawn.getPlayerSide());
    }
}