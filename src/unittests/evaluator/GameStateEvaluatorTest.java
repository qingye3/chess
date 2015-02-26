package unittests.evaluator;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.data.piece.King;
import chess.lib.data.piece.Queen;
import chess.lib.data.piece.Rook;
import chess.lib.datatype.GameStatus;
import chess.lib.datatype.PlayerSide;
import chess.lib.evaluator.GameStateEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

public class GameStateEvaluatorTest extends TestCase {
    GameStateEvaluator evaluator;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        evaluator = new GameStateEvaluator();
    }
    @Test
    public void testTwoKings() throws Exception{
        GameState state = new GameState();
        state.addPiece(new Position("a1"), new King());
        state.addPiece(new Position("a2"), new King());
        assertEquals(evaluator.evaluate(state), GameStatus.IMPOSSIBLE);
    }

    @Test
    public void testOpponentChecked() throws Exception{
        GameState state = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());
        state.addPiece(new Position("a2"), new Rook());
        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("a8"), blackKing);
        assertEquals(evaluator.evaluate(state), GameStatus.IMPOSSIBLE);
    }

    @Test
    public void testIsCheckedTrue() throws Exception {
        GameState state = new GameState();

        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("f2"), blackKing);

        Rook blackRook= new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("a3"), blackRook);

        assertTrue(evaluator.isChecked(state, PlayerSide.WHITE));
    }

    @Test
    public void testIsCheckedFalse() throws Exception {
        GameState state = new GameState();

        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("f2"), blackKing);

        assertFalse(evaluator.isChecked(state, PlayerSide.WHITE));
    }

    @Test
    public void testCheckmate() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("c2"), blackKing);

        Queen blackQueen = new Queen();
        blackQueen.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("b2"), blackQueen);

        assertEquals(evaluator.evaluate(state), GameStatus.BLACKWINS);
    }

    @Test
    public void testCheckmate2() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.BLACK);
        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("a1"), blackKing);

        King whiteKing = new King();
        whiteKing.setPlayerSide(PlayerSide.WHITE);
        state.addPiece(new Position("h2"), whiteKing);

        Rook whiteRook = new Rook();
        whiteRook.setPlayerSide(PlayerSide.WHITE);
        state.addPiece(new Position("h1"), whiteRook);

        Rook whiteRook2 = new Rook();
        whiteRook2.setPlayerSide(PlayerSide.WHITE);
        state.addPiece(new Position("g2"), whiteRook2);

        assertEquals(evaluator.evaluate(state), GameStatus.WHITEWINS);
    }

    @Test
    public void testNormal() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("g2"), blackKing);

        Rook blackRook = new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h1"), blackRook);

        Rook blackRook2 = new Rook();
        blackRook2.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h2"), blackRook2);

        assertEquals(evaluator.evaluate(state), GameStatus.NORMAL);
    }

    @Test
    public void testStalemate() throws Exception{
        GameState state  = new GameState();
        state.setCurrentSide(PlayerSide.WHITE);
        state.addPiece(new Position("a1"), new King());

        King blackKing = new King();
        blackKing.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h1"), blackKing);

        Rook blackRook = new Rook();
        blackRook.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("h2"), blackRook);

        Rook blackRook2 = new Rook();
        blackRook2.setPlayerSide(PlayerSide.BLACK);
        state.addPiece(new Position("b2"), blackRook2);

        assertEquals(evaluator.evaluate(state), GameStatus.STALEMATE);
    }
}