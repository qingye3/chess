package unittests.validator;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.data.piece.Bishop;
import chess.lib.exception.ChessException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import chess.lib.validator.BishopMoveValidator;

public class BishopMoveValidatorTest extends TestCase {

    GameState gameState;
    BishopMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new BishopMoveValidator();
    }
    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(4, 4), new Position(6, 6));
        validator.validate(gameState, new Position(4, 4), new Position(2, 2));
    }

    @Test
    public void testJump() throws Exception{
        Bishop bishop = new Bishop();
        gameState.addPiece(new Position(5,5), bishop);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
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
        Bishop bishop1 = new Bishop();
        Bishop bishop2 = new Bishop();
        gameState.addPiece(new Position(4, 4), bishop1);
        gameState.addPiece(new Position(6,6), bishop2);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(6, 6));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

}