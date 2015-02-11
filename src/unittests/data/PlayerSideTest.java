package unittests.data;

import data.PlayerSide;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerSideTest {
    @Test
    public void testGetOpponentSide(){
        PlayerSide white = PlayerSide.WHITE;
        PlayerSide black = PlayerSide.BLACK;
        assertEquals(white.getOpponentSide(), black);
        assertEquals(black.getOpponentSide(), white);
    }

}