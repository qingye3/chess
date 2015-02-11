package unittests.data;

import data.Position;
import javafx.geometry.Pos;
import junit.framework.TestCase;
import org.junit.Test;

public class PositionTest extends TestCase {

    @Test
    public void testGetters() throws Exception {
        Position pos = new Position(3, 2);
        assertEquals(3, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test
    public void testSetters() throws Exception {
        Position pos = new Position();
        pos.setX(6);
        pos.setY(0);
        assertEquals(6, pos.getX());
        assertEquals(0, pos.getY());
    }

    @Test
    public void testToString() throws Exception {
        Position pos = new Position(3,4);
        assertEquals("d5", pos.toString());
    }

    @Test
    public void testCopy() throws Exception{
        Position pos1 = new Position(3,4);
        Position pos2 = new Position(pos1);

        //check if pos2 is copied correctly
        assertEquals(3, pos2.getX());
        assertEquals(4, pos2.getY());

        //check if pos1 still hold the old value
        pos2.setX(5);
        pos2.setY(6);
        assertEquals(3, pos1.getX());
        assertEquals(4, pos1.getY());
    }

    @Test
    public void testEqual() throws Exception{
        Position pos1 = new Position(3,4);
        Position pos2 = new Position(2,4);
        Position pos3 = new Position(3,4);
        assertFalse(pos1.equals(pos2));
        assertFalse(pos1.equals(new String()));
        assertFalse(pos1.equals(null));
        assertTrue(pos1.equals(pos3));

    }
}