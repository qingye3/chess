package chess.lib.validator;

import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 * Abstract validator
 */
public abstract class MoveValidator {
    /**
     * Check if a move is valid
     * Does not check the origin
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    public abstract void validate(GameState gameState, Position origin, Position destination) throws ChessException;

    protected boolean isInStraightLine(Position origin, Position destination) {
        return (origin.getX() == destination.getX() || origin.getY() == destination.getY());
    }

    protected boolean isInDiagonal(Position origin, Position destination) {
        return getDisplacementInX(origin, destination) ==
                getDisplacementInY(origin, destination);
    }

    protected boolean isKnightMove(Position origin, Position destination) {
        int xDisplacement = getDisplacementInX(destination, origin);
        int yDisplacement = getDisplacementInY(destination, origin);
        return (xDisplacement == 2 && yDisplacement == 1) ||
                (xDisplacement == 1 && yDisplacement == 2) ;
    }

    protected boolean isDiagonalPathClear(GameState gameState, Position origin, Position destination) {
        int xStep = getDiagonalXDirection(origin, destination);
        int yStep = getDiagonalYDirection(origin, destination);

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

    protected boolean isStraightLinePathClear(GameState gameState, Position origin, Position destination) {
        int stepX = getStraightXDirection(origin, destination);
        int stepY = getStraightYDirection(origin, destination);

        int currX = origin.getX();
        int currY = origin.getY();

        for (int i = 0; i < getStraightMoveSteps(origin, destination); i++ ){
            currX += stepX;
            currY += stepY;

            if (gameState.getPiece(new Position(currX, currY)) != null){
                return false;
            }
        }
        return true;
    }

    protected int getDisplacementInY(Position origin, Position destination) {
        return abs(destination.getY() - origin.getY());
    }

    protected int getDisplacementInX(Position origin, Position destination) {
        return abs(destination.getX() - origin.getX());
    }

    private int getDiagonalXDirection(Position origin, Position destination) {
        if (destination.getX() < origin.getX()) {
            return -1;
        } else{
            return 1;
        }
    }

    private int getDiagonalYDirection(Position origin, Position destination) {
        if (destination.getY() < origin.getY()) {
            return -1;
        } else{
            return 1;
        }
    }

    private int getStraightMoveSteps(Position origin, Position destination) {
        int steps;
        if (getStraightXDirection(origin, destination) != 0) {
            steps = abs(origin.getX() - destination.getX()) - 1;
            return steps;
        }
        assert(getStraightYDirection(origin, destination) != 0);
        steps = abs(origin.getY() - destination.getY()) - 1;
        return steps;
    }

   private int getStraightXDirection(Position origin, Position destination) {
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

    private int getStraightYDirection(Position origin, Position destination) {
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

