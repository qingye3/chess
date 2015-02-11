package evaluator;

import controller.MoveController;
import data.GameState;
import data.Position;
import datatype.GameStatus;
import datatype.PlayerSide;
import exception.ChessException;

import java.util.ArrayList;

/**
 * Created by Qing on 2/11/2015.
 *
 * An evaluator for game state. A game state can be evaluated into 3 result
 * IMPOSSIBLE: a game state that is impossible or a game state occurred after an invalid move
 * BLACKWINS: a game state where black has won
 * WHITEWINS: a game state where white has won
 * STALEMATE: a game state that is stalemate
 * NORMAL: nothing interesting
 */
public class GameStateEvaluator {
    public GameStatus evaluate(GameState gameState){
        //A move that can immediately capture opponent's King is not possible in chess
        if (isKingCountWrong(gameState) || isCurrOpponentChecked(gameState))
        {
            return GameStatus.IMPOSSIBLE;
        }

        if (isNoEscape(gameState)) {
            if (isCurrSideChecked(gameState)) {
                if (gameState.getCurrentSide() == PlayerSide.WHITE) {
                    return GameStatus.BLACKWINS;
                } else {
                    return GameStatus.WHITEWINS;
                }
            } else {
                return GameStatus.STALEMATE;
            }
        }
        return GameStatus.NORMAL;
    }

    private boolean isKingCountWrong(GameState gameState) {
        return isKingCountWrong(gameState, PlayerSide.BLACK) || isKingCountWrong(gameState, PlayerSide.WHITE);
    }

    private boolean isKingCountWrong(GameState gameState, PlayerSide side) {
        int kingCounts = 0;
        ArrayList<Position> positions = gameState.getPositions(side);
        for (Position pos : positions){
            //K for king... standard chess notation
            if (gameState.getPiece(pos).toString() == "K"){
                kingCounts += 1;
            }
        }
        return kingCounts != 1;
    }

    private boolean isCurrSideChecked(GameState gameState) {
        return isChecked(gameState, gameState.getCurrentSide());
    }

    private boolean isCurrOpponentChecked(GameState gameState) {
        return isChecked(gameState, gameState.getCurrentSide().getOpponentSide());
    }

    private boolean isNoEscape(GameState gameState) {
        PlayerSide side = gameState.getCurrentSide();
        ArrayList<Position> positions = gameState.getPositions(side);
        Position kingPosition = getKingPosition(gameState, side);

        //TODO: refactor this
        for (Position pos : positions){
            MoveController controller = gameState.getPiece(pos).getMoveController();
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    try {
                        GameState nextState = controller.move(gameState, pos, new Position(x, y));
                        if (!isChecked(nextState, side)) {
                            return false;
                        }
                    } catch (ChessException e) {
                    }
                }
            }
        }
        return true;

    }

    public boolean isChecked(GameState gameState, PlayerSide side){
        Position kingPosition = getKingPosition(gameState, side);
        ArrayList<Position> opponentPositions = gameState.getPositions(side.getOpponentSide());

        for (Position pos : opponentPositions){
            MoveController controller = gameState.getPiece(pos).getMoveController();
            try {
                controller.move(gameState, pos, kingPosition);
                return true;
            } catch (ChessException e){
            }
        }
        return false;
    }

    private Position getKingPosition(GameState gameState, PlayerSide side) {
        ArrayList<Position> positions = gameState.getPositions(side);
        for (Position pos : positions){
            //K for king... standard chess notation
            if (gameState.getPiece(pos).toString() == "K"){
                return new Position(pos);
            }
        }
        return null;
    }
}
