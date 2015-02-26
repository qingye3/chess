package unittests.validator;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.data.piece.Knight;
import chess.lib.exception.ChessException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import chess.lib.validator.KnightMoveValidator;

public class KnightMoveValidatorTest extends TestCase {
    GameState gameState;
    KnightMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new KnightMoveValidator();
    }
    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(4, 4), new Position(6, 3));
        validator.validate(gameState, new Position(4, 4), new Position(3, 6));
    }

    @Test
    public void testInvalidMove() throws Exception{
        try{
            validator.validate(gameState, new Position(4, 4), new Position(5, 7));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testNoMove() throws Exception{
        try{
            validator.validate(gameState, new Position(4, 4), new Position(4, 4));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testSelfCapture() throws Exception{
        Knight knight1 = new Knight();
        Knight knight2 = new Knight();
        gameState.addPiece(new Position(4, 4), knight1);
        gameState.addPiece(new Position(6, 5), knight2);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 5));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }
}