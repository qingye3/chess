package validator;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import data.piece.Rook;
import exception.ChessBoardException;
import exception.ChessException;
import javafx.geometry.Pos;

/**
 * Created by Qing on 2/11/2015.
 */
public class RookMoveValidator implements MoveValidator{
    private PositionValidator positionValidator = new PositionValidator();
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Rook rook = (Rook) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == rook.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        if (!isAligned(origin, destination)){
            throw new ChessException("Invalid Move: Rook can only move in straight line");
        }
        if (!isPathClear(gameState, origin, destination)){
            throw new ChessBoardException("Invalid Move: Rook cannot jump over another piece");
        }
    }

    private boolean isAligned(Position origin, Position destination) {
        return origin.getX() == destination.getX() || origin.getY() == destination.getY();
    }

    private boolean isPathClear(GameState gameState, Position origin, Position destination) {
        if (origin.getX() == destination.getX()){
            int min = min(origin.getY(), destination.getY());
            int max = max(origin.getY(), destination.getY());
            for (int y = min + 1; y < max; y++){
                if (gameState.getPiece(new Position(origin.getX(), y)) != null){
                    return false;
                }
            }
        }
        if (origin.getY() == destination.getY()){
            int min = min(origin.getX(), destination.getX());
            int max = max(origin.getX(), destination.getX());
            for (int x = min + 1; x < max; x++){
                if (gameState.getPiece(new Position(origin.getX(), x)) != null){
                    return false;
                }
            }
        }
        return true;
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        }  else {
            return b;
        }
    }

    private int min(int a, int b) {
        if (a < b) {
            return a;
        }  else {
            return b;
        }
    }
}
