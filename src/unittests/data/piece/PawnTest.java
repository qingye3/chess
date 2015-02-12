package unittests.data.piece;

import controller.PawnMoveController;
import data.piece.Pawn;
import datatype.PlayerSide;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PawnTest {

    private Pawn pawn;

    @Before
    public void setUp() throws Exception {
        pawn = new Pawn();
        pawn.setPlayerSide(PlayerSide.BLACK);

        pawn.setFirstMovementRound(5);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(pawn.getMoveController() instanceof PawnMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("", pawn.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        Pawn copy = (Pawn) pawn.deepCopy();
        assertEquals("", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
        assertEquals(5, copy.getFirstMovementRound());
    }
}