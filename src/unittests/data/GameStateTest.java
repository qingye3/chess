package unittests.data;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.King;
import chess.data.piece.Pawn;
import chess.data.piece.Queen;
import chess.datatype.PlayerSide;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
    private GameState initialState;

    @Before
    public void setUp() throws Exception {
        initialState = new GameState();
        initialState.setCurrentRound(5);
        initialState.setCurrentSide(PlayerSide.BLACK);
        Pawn whitePawn = new Pawn();
        whitePawn.setPlayerSide(PlayerSide.WHITE);
        Pawn blackPawn = new Pawn();
        blackPawn.setPlayerSide(PlayerSide.BLACK);
        initialState.addPiece(new Position(2, 3), whitePawn);
        initialState.addPiece(new Position(4, 6), blackPawn);
    }

    @Test
    public void testGetCurrentSide() throws Exception {
        assertEquals(PlayerSide.BLACK, initialState.getCurrentSide());
    }

    @Test
    public void testGetCurrentRound() throws Exception {
        assertEquals(5, initialState.getCurrentRound());

    }

    @Test
    public void testGetPiece() throws Exception {
        ChessPiece piece = initialState.getPiece(new Position(4, 6));
        assertEquals(PlayerSide.BLACK,piece.getPlayerSide());
        assertTrue(piece instanceof Pawn);
    }

    @Test
    public void testRemovePiece() throws Exception {
        initialState.addPiece(new Position(4, 4), new Pawn());
        initialState.removePiece(new Position(4, 4));
        assertNull(initialState.getPiece(new Position(4, 4)));

    }

    @Test
    public void testCopyConstructor() throws Exception {
        GameState copy = new GameState(initialState);

        ChessPiece piece = copy.getPiece(new Position(4, 6));
        assertEquals(PlayerSide.BLACK,piece.getPlayerSide());

        piece = copy.getPiece(new Position(2, 3));
        assertEquals(PlayerSide.WHITE,piece.getPlayerSide());
    }

    @Test
    public void testGetPositions() throws Exception {
        GameState state = new GameState();
        state.addPiece(new Position(2, 3), new Pawn());
        state.addPiece(new Position(4, 6), new King());
        state.addPiece(new Position(4, 5), new Queen());
        assertEquals(state.getPositions(PlayerSide.WHITE).size(), 3);
        assertEquals(state.getPositions(PlayerSide.BLACK).size(), 0);
    }

}