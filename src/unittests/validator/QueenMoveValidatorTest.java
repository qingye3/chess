package unittests.validator;

import data.GameState;
import data.Position;
import data.piece.Queen;
import exception.ChessException;
import org.junit.Before;
import org.junit.Test;
import validator.QueenMoveValidator;

import static org.junit.Assert.*;

public class QueenMoveValidatorTest{
    GameState gameState;
    QueenMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new QueenMoveValidator();
    }

    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(4, 4), new Position(4, 6));
        validator.validate(gameState, new Position(4, 4), new Position(4, 2));
        validator.validate(gameState, new Position(4, 4), new Position(6, 4));
        validator.validate(gameState, new Position(4, 4), new Position(2, 4));
        validator.validate(gameState, new Position(4, 4), new Position(6, 6));
        validator.validate(gameState, new Position(4, 4), new Position(2, 2));
    }

    @Test
    public void testJump() throws Exception{
        Queen queen = new Queen();
        Queen queen2 = new Queen();
        gameState.addPiece(new Position(4,5), queen);
        gameState.addPiece(new Position(5,5), queen2);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(4, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        gameState.removePiece(new Position(4,5));
        gameState.removePiece(new Position(5,5));
    }

    @Test
    public void testInvalidMove() throws Exception{
        try{
            validator.validate(gameState, new Position(4, 4), new Position(5, 6));
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
        Queen queen1 = new Queen();
        Queen queen2 = new Queen();
        Queen queen3 = new Queen();
        gameState.addPiece(new Position(4,4), queen1);
        gameState.addPiece(new Position(4,7), queen2);
        gameState.addPiece(new Position(6,6), queen3);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(4, 7));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }
}