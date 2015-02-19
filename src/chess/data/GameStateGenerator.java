package chess.data;

import chess.constants.Constants;
import chess.data.piece.*;
import chess.datatype.PlayerSide;

/**
 * Generate useful game states.
 * Currently, only have standard opening state
 * Created by Qing on 2/18/2015.
 */
public class GameStateGenerator {
    /**
     *
     * @return a game state representing the standard chess setup
     */
    public static GameState standardOpening(){
        GameState gameState = new GameState();
        for (int i = 0; i < Constants.BOARDWIDTH; i++){
            addPieceToState(gameState, new Pawn(), new Position(i, 1), PlayerSide.WHITE);
            addPieceToState(gameState, new Pawn(), new Position(i, Constants.BOARDHEIGHT - 2), PlayerSide.BLACK);
        }
        addPieceToState(gameState, new Rook(), new Position("a1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Rook(), new Position("h1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Rook(), new Position("a8"), PlayerSide.BLACK);
        addPieceToState(gameState, new Rook(), new Position("h8"), PlayerSide.BLACK);
        addPieceToState(gameState, new Knight(), new Position("b1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Knight(), new Position("g1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Knight(), new Position("b8"), PlayerSide.BLACK);
        addPieceToState(gameState, new Knight(), new Position("g8"), PlayerSide.BLACK);
        addPieceToState(gameState, new Bishop(), new Position("c1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Bishop(), new Position("f1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Bishop(), new Position("c8"), PlayerSide.BLACK);
        addPieceToState(gameState, new Bishop(), new Position("f8"), PlayerSide.BLACK);
        addPieceToState(gameState, new Queen(), new Position("d1"), PlayerSide.WHITE);
        addPieceToState(gameState, new Queen(), new Position("d8"), PlayerSide.BLACK);
        addPieceToState(gameState, new King(), new Position("e1"), PlayerSide.WHITE);
        addPieceToState(gameState, new King(), new Position("e8"), PlayerSide.BLACK);
        return gameState;
    }
    private static void addPieceToState(GameState gameState, ChessPiece piece, Position position, PlayerSide side){
        piece.setPlayerSide(side);
        gameState.addPiece(position, piece);
    }
}
