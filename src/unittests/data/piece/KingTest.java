package unittests.data.piece;

import chess.controller.KingMoveController;
import chess.data.piece.ChessPiece;
import chess.data.piece.King;
import chess.datatype.PlayerSide;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class KingTest extends TestCase {
    private King king;

    @Before
    public void setUp() throws Exception {
        king = new King();
        king.setPlayerSide(PlayerSide.BLACK);
    }

    @Test
    public void testGetMoveController() throws Exception {
        assertTrue(king.getMoveController() instanceof KingMoveController);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("K", king.toString());
    }

    @Test
    public void testDeepCopy() throws Exception {
        ChessPiece copy = king.deepCopy();
        assertEquals("K", copy.toString());
        assertEquals(PlayerSide.BLACK, copy.getPlayerSide());
    }

    @Test
    public void testGetHasMoved() throws Exception{
        assertFalse(king.isHasMoved());
    }

    @Test
    public void testSetHasMoved() throws Exception{
        king.setHasMoved(true);
        assertTrue(king.isHasMoved());
        king.setHasMoved(false);
        assertFalse(king.isHasMoved());
    }
}