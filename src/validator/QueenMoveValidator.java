package validator;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import data.piece.Queen;
import exception.ChessBoardException;
import exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class QueenMoveValidator implements MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Queen queen = (Queen) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == queen.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        if (!isAligned(origin, destination)){
            throw new ChessException("Invalid Move: Queen can only move in straight line or in diagonal direction");
        }

        if (!isPathClear(gameState, origin, destination)){
            throw new ChessBoardException("Invalid Move: Rook cannot jump over another piece");
        }
    }

    private boolean isAligned(Position origin, Position destination) {
        return isInDiagonal(origin, destination) || isInStraightLine(origin, destination);
    }

    private boolean isInStraightLine(Position origin, Position destination) {
        return (origin.getX() == destination.getX() || origin.getY() == destination.getY());
    }

    private boolean isInDiagonal(Position origin, Position destination) {
        return getDisplacementInX(origin, destination) ==
                getDisplacementInY(origin, destination);
    }

    private int getDisplacementInY(Position origin, Position destination) {
        return abs(destination.getY() - origin.getY());
    }

    private int getDisplacementInX(Position origin, Position destination) {
        return abs(destination.getX() - origin.getX());
    }

    private boolean isPathClear(GameState gameState, Position origin, Position destination) {
        if (isInStraightLine(origin, destination)){
            return isStraightLinePathClear(gameState, origin, destination);
        }
        assert(isInDiagonal(origin, destination));
        return isDiagonalClear(gameState, origin, destination);
    }

    private boolean isDiagonalClear(GameState gameState, Position origin, Position destination) {
        int xStep = getXStep(origin, destination);
        int yStep = getYStep(origin, destination);

        int curX = origin.getX() + xStep;
        int curY = origin.getY() + yStep;

        for (int i = 0; i < getDisplacementInX(origin, destination) - 1; i++)
        {
            if (gameState.getPiece(new Position(curX, curY)) != null)
            {
                return false;
            }
            curX += xStep;
            curY += yStep;
        }
        return true;
    }

    private boolean isStraightLinePathClear(GameState gameState, Position origin, Position destination) {
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

    private int getXStep(Position origin, Position destination) {
        if (destination.getX() < origin.getX()) {
            return -1;
        } else{
            return 1;
        }
    }

    private int getYStep(Position origin, Position destination) {
        if (destination.getY() < origin.getY()) {
            return -1;
        } else{
            return 1;
        }
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
