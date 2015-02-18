package unittests.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.Pawn;
import chess.datatype.PlayerSide;
import chess.exception.ChessException;
import org.junit.Before;
import org.junit.Test;
import chess.validator.PawnMoveValidator;

import static org.junit.Assert.fail;

public class PawnMoveValidatorTest {
    GameState gameState;
    PawnMoveValidator validator;

    @Before
    public void setUp() throws Exception {
        gameState = new GameState();
        validator = new PawnMoveValidator();
    }

    private void setUp0() throws Exception{
        gameState = new GameState();

    }
    private void setUp1() throws Exception{
        gameState = new GameState();
        Pawn whitePawn1 = new Pawn();
        gameState.addPiece(new Position("a2"), whitePawn1);
        Pawn whitePawn2 = new Pawn();
        gameState.addPiece(new Position("c3"), whitePawn2);
        Pawn whitePawn3 = new Pawn();
        gameState.addPiece(new Position("g6"), whitePawn3);

        Pawn blackPawn1 = new Pawn();
        blackPawn1.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("h7"), blackPawn1);

        Pawn blackPawn2 = new Pawn();
        blackPawn2.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("f6"), blackPawn2);

        Pawn blackPawn3 = new Pawn();
        blackPawn3.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("b3"), blackPawn3);
    }

    private void setUp2() throws Exception{
        gameState = new GameState();
        Pawn whitePawn1 = new Pawn();
        gameState.addPiece(new Position("a2"), whitePawn1);
        Pawn whitePawn2 = new Pawn();
        gameState.addPiece(new Position("b3"), whitePawn2);
        Pawn whitePawn3 = new Pawn();
        gameState.addPiece(new Position("h6"), whitePawn3);
        Pawn whitePawn4 = new Pawn();
        gameState.addPiece(new Position("g5"), whitePawn4);

        Pawn blackPawn1 = new Pawn();
        blackPawn1.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("h7"), blackPawn1);

        Pawn blackPawn2 = new Pawn();
        blackPawn2.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("g6"), blackPawn2);

        Pawn blackPawn3 = new Pawn();
        blackPawn3.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("a3"), blackPawn3);

        Pawn blackPawn4 = new Pawn();
        blackPawn3.setPlayerSide(PlayerSide.BLACK);
        gameState.addPiece(new Position("b4"), blackPawn4);
    }

    @Test
    public void testValidMove1() throws Exception {
        setUp1();
        validator.validate(gameState, new Position("a2"), new Position("a3"));
        validator.validate(gameState, new Position("a2"), new Position("a4"));
        validator.validate(gameState, new Position("a2"), new Position("b3"));
        validator.validate(gameState, new Position("c3"), new Position("c4"));

        validator.validate(gameState, new Position("h7"), new Position("h6"));
        validator.validate(gameState, new Position("h7"), new Position("h5"));
        validator.validate(gameState, new Position("h7"), new Position("g6"));
        validator.validate(gameState, new Position("f6"), new Position("f5"));
    }

    @Test
    public void testNoMove() throws Exception{
        setUp0();
        try{
            validator.validate(gameState, new Position(4, 4), new Position(4, 4));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testSelfCapture() throws Exception{
        Pawn bishop1 = new Pawn();
        Pawn bishop2 = new Pawn();
        gameState.addPiece(new Position(1,1), bishop1);
        gameState.addPiece(new Position(2,2), bishop2);
        try{
            validator.validate(gameState, new Position(1, 1), new Position(2, 2));
            fail("Exception should have been thrown");
        } catch (ChessException e){
        }
    }

    @Test
    public void testJump() throws Exception{
        setUp2();
        testMoveFail("a2", "e2");
        testMoveFail("a2", "a3");
        testMoveFail("a2", "a4");
        testMoveFail("b3", "b4");
        testMoveFail("h7", "h6");
        testMoveFail("h7", "h5");
        testMoveFail("g6", "g5");
    }

    private void testMoveFail(String origin, String dest) {
        try{
            validator.validate(gameState, new Position(origin), new Position(dest));
            fail("Exception should have been thrown");
        } catch (ChessException e){}
    }
}