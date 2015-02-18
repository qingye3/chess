package unittests.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.Princess;
import chess.exception.ChessException;
import chess.validator.PrincessMoveValidator;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrincessMoveValidatorTest extends TestCase {

    GameState gameState;
    PrincessMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new PrincessMoveValidator();
    }

    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(4, 4), new Position(6, 6));
        validator.validate(gameState, new Position(4, 4), new Position(6, 6));
        validator.validate(gameState, new Position(4, 4), new Position(2, 6));
        validator.validate(gameState, new Position(4, 4), new Position(6, 2));
        validator.validate(gameState, new Position(4, 4), new Position(6, 3));
        validator.validate(gameState, new Position(4, 4), new Position(3, 6));
    }

    @Test
    public void testJump() throws Exception{
        Princess princess = new Princess();
        Princess princess2 = new Princess();
        gameState.addPiece(new Position(4,4), princess);
        gameState.addPiece(new Position(5,5), princess2);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        try{
            validator.validate(gameState, new Position(5, 5), new Position(3, 3));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        gameState.removePiece(new Position(4,4));
        gameState.removePiece(new Position(5,5));
    }

    @Test
    public void testInvalidMove() throws Exception{
        try{
            validator.validate(gameState, new Position(4, 4), new Position(1, 5));
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
        Princess princess1 = new Princess();
        Princess princess2 = new Princess();
        Princess princess3 = new Princess();
        gameState.addPiece(new Position(4,4), princess1);
        gameState.addPiece(new Position(5,6), princess2);
        gameState.addPiece(new Position(6,6), princess3);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        try{
            validator.validate(gameState, new Position(4, 4), new Position(5, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        gameState.removePiece(new Position(4,4));
        gameState.removePiece(new Position(5,6));
        gameState.removePiece(new Position(6,6));
    }
}