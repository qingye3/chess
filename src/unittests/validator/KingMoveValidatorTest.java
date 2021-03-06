package unittests.validator;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.data.piece.Bishop;
import chess.lib.data.piece.King;
import chess.lib.exception.ChessException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import chess.lib.validator.KingMoveValidator;

public class KingMoveValidatorTest extends TestCase {
    GameState gameState;
    KingMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new KingMoveValidator();
    }

    @Test
    public void testValidMove() throws Exception {
        validator.validate(gameState, new Position(4, 4), new Position(4, 5));
        validator.validate(gameState, new Position(4, 4), new Position(5, 5));
        validator.validate(gameState, new Position(4, 4), new Position(3, 5));
        validator.validate(gameState, new Position(4, 4), new Position(3, 4));
        validator.validate(gameState, new Position(4, 4), new Position(5, 4));
        validator.validate(gameState, new Position(4, 4), new Position(5, 3));
        validator.validate(gameState, new Position(4, 4), new Position(4, 3));
        validator.validate(gameState, new Position(4, 4), new Position(3, 3));
    }

    @Test
    public void testBadMove() throws Exception{
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
        Bishop bishop = new Bishop();
        King king = new King();
        gameState.addPiece(new Position(4, 4), king);
        gameState.addPiece(new Position(5, 5), bishop);
        try{
            validator.validate(gameState, new Position(4, 4), new Position(5, 5));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }
}