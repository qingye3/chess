package unittests.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.Empress;
import chess.exception.ChessException;
import chess.validator.EmpressMoveValidator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmpressMoveValidatorTest extends TestCase {
    GameState gameState;
    EmpressMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new EmpressMoveValidator();
    }

    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(4, 4), new Position(4, 6));
        validator.validate(gameState, new Position(4, 4), new Position(6, 4));
        validator.validate(gameState, new Position(4, 4), new Position(2, 4));
        validator.validate(gameState, new Position(4, 4), new Position(4, 2));
        validator.validate(gameState, new Position(4, 4), new Position(6, 3));
        validator.validate(gameState, new Position(4, 4), new Position(3, 6));
    }

    @Test
    public void testJump() throws Exception{
        Empress empress = new Empress();
        Empress empress2 = new Empress();
        gameState.addPiece(new Position(4,4), empress);
        gameState.addPiece(new Position(4,5), empress2);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(4, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        try{
            validator.validate(gameState, new Position(4, 5), new Position(4, 3));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        gameState.removePiece(new Position(4,4));
        gameState.removePiece(new Position(4,5));
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
        Empress empress1 = new Empress();
        Empress empress2 = new Empress();
        Empress empress3 = new Empress();
        gameState.addPiece(new Position(4,4), empress1);
        gameState.addPiece(new Position(4,5), empress2);
        gameState.addPiece(new Position(5,6), empress3);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(4, 5));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        try{
            validator.validate(gameState, new Position(4, 4), new Position(5, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        gameState.removePiece(new Position(4,4));
        gameState.removePiece(new Position(4,5));
        gameState.removePiece(new Position(5,6));
    }

}