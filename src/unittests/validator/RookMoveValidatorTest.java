package unittests.validator;

import data.GameState;
import data.Position;
import data.piece.Rook;
import datatype.PlayerSide;
import exception.ChessException;
import org.junit.Before;
import org.junit.Test;
import validator.RookMoveValidator;

import static org.junit.Assert.fail;

public class RookMoveValidatorTest {
    GameState gameState;
    RookMoveValidator validator;
    @Before
    public void setUp() throws Exception {
        Rook whiteRook = new Rook();
        whiteRook.setPlayerSide(PlayerSide.WHITE);

        Rook whiteRook2 = new Rook();
        whiteRook2.setPlayerSide(PlayerSide.WHITE);

        Rook blackRook = new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);

        gameState = new GameState();
        gameState.addPiece(new Position(3,3), whiteRook);
        gameState.addPiece(new Position(3,4), whiteRook2);
        gameState.addPiece(new Position(1,3), blackRook);

        validator = new RookMoveValidator();
    }

    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(3,3), new Position(1,3));
        validator.validate(gameState, new Position(3,4), new Position(3,7));
        validator.validate(gameState, new Position(3,3), new Position(3,2));
        validator.validate(gameState, new Position(3,3), new Position(5,3));
    }

    @Test
    public void testJump() throws Exception {
        try {
            validator.validate(gameState, new Position(3, 3), new Position(0, 3));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testCaptureOwn() throws Exception {
        try {
            validator.validate(gameState, new Position(3, 3), new Position(3, 4));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testBadMove() throws Exception {
        try {
            validator.validate(gameState, new Position(3, 3), new Position(2, 4));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testNoMove() throws Exception {
        try {
            validator.validate(gameState, new Position(3, 3), new Position(3, 3));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }
}