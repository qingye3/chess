package unittests.exception;

import chess.lib.exception.ChessException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChessExceptionTest {

    @Test
    public void testNullException(){
        try {
            throw new ChessException(null);
        } catch (ChessException e){
            assertEquals("An unexpected Chess error has occurred.", e.getMessage());
        }
    }


    @Test
    public void testException(){
        try {
            throw new ChessException("some stuff");
        } catch (ChessException e){
            assertEquals("some stuff", e.getMessage());
        }
    }

}