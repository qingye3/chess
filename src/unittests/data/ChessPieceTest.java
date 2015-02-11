package unittests.data;

import controller.MoveController;
import data.piece.ChessPiece;
import data.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class MockChessPiece extends ChessPiece {
    @Override
    public MoveController getMoveController() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}

public class ChessPieceTest {
    private ChessPiece chessPiece;

    @Before
    public void setUp(){
        chessPiece = new MockChessPiece();
        chessPiece.setPosition(new Position(2,3));
    }

    @Test
    public void testGet(){
        assertEquals(new Position(2,3), chessPiece.getPosition());
    }
}