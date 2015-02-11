package unittests.data;

import controller.PawnMoveController;
import data.piece.ChessPiece;
import data.piece.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessPiece pawn;

    @Before
    public void setUp() throws Exception {
        pawn = new Pawn();
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(pawn.getMoveController() instanceof PawnMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("", pawn.toString());
    }
}