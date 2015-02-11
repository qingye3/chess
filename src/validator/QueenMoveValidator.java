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
        int xStep = getDiagonalXStep(origin, destination);
        int yStep = getDiagonalYStep(origin, destination);

        int curX = origin.getX();
        int curY = origin.getY();


        for (int i = 0; i < getDisplacementInX(origin, destination) - 1; i++)
        {
            curX += xStep;
            curY += yStep;
            if (gameState.getPiece(new Position(curX, curY)) != null)
            {
                return false;
            }
        }
        return true;
    }

    private boolean isStraightLinePathClear(GameState gameState, Position origin, Position destination) {
        int stepX = getStepX(origin, destination);
        int stepY = getStepY(origin, destination);

        int currX = origin.getX();
        int currY = origin.getY();

        for (int i = 0; i < getSteps(origin, destination); i++ ){
            currX += stepX;
            currY += stepY;

            if (gameState.getPiece(new Position(currX, currY)) != null){
                return false;
            }
        }
        return true;
    }

    private int getDiagonalXStep(Position origin, Position destination) {
        if (destination.getX() < origin.getX()) {
            return -1;
        } else{
            return 1;
        }
    }

    private int getDiagonalYStep(Position origin, Position destination) {
        if (destination.getY() < origin.getY()) {
            return -1;
        } else{
            return 1;
        }
    }

    private int getSteps(Position origin, Position destination) {
        int steps;
        if (getStepX(origin, destination) != 0) {
            steps = abs(origin.getX() - destination.getX()) - 1;
            return steps;
        }
        assert(getStepY(origin, destination) != 0);
        steps = abs(origin.getY() - destination.getY()) - 1;
        return steps;
    }

    private int getStepX(Position origin, Position destination) {
        int stepX = 0;
        if (origin.getX() < destination.getX())
        {
            stepX = 1;
        }
        if (origin.getX() > destination.getX())
        {
            stepX = -1;
        }
        return stepX;
    }

    private int getStepY(Position origin, Position destination) {
        int stepY = 0;
        if (origin.getY() < destination.getY())
        {
            stepY = 1;
        }
        if (origin.getY() > destination.getY())
        {
            stepY = -1;
        }
        return stepY;
    }
}
