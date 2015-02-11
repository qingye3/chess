package unittests.exception;

import exception.ChessBoardException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardExceptionTest {

    @Test
    public void testNullException(){
        try {
            throw new ChessBoardException(null);
        } catch (ChessBoardException e){
            assertEquals("An unexpected Chess error has occurred.", e.getMessage());
        }
    }


    @Test
    public void testException(){
        try {
            throw new ChessBoardException("some stuff");
        } catch (ChessBoardException e){
            assertEquals("some stuff", e.getMessage());
        }
    }

}