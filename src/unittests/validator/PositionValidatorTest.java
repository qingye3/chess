package unittests.validator;

import data.Position;
import exception.ChessBoardException;
import exception.ChessException;
import junit.framework.TestCase;
import org.junit.Test;
import validator.PositionValidator;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class PositionValidatorTest extends TestCase {
    PositionValidator validator;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        validator = new PositionValidator();
    }

    @Test
    public void testGoodPosition() throws Exception{
        Position pos = new Position(2,3);
        validator.validate(pos);
    }

    @Test
    public void testBadPosition() throws Exception{
        Position pos = new Position(-1,3);
        try {
            validator.validate(pos);
            fail("Exception should have been thrown");
        }catch (ChessBoardException e){
        }
    }

}