package validator;

import data.GameState;
import data.Position;
import data.piece.ChessPiece;
import data.piece.Rook;
import exception.ChessException;

import static java.lang.Math.abs;

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
            throw new ChessException("Invalid Move: Rook cannot jump over another piece");
        }
    }

    private boolean isAligned(Position origin, Position destination) {
        return origin.getX() == destination.getX() || origin.getY() == destination.getY();
    }

    private boolean isPathClear(GameState gameState, Position origin, Position destination) {
        int stepX = getStepX(origin, destination);
        int stepY = getStepY(origin, destination);

        int currX = origin.getX();
        int currY = origin.getY();
        int steps = getSteps(origin, destination);

        for (int i = 0; i < steps; i++ ){
            currX += stepX;
            currY += stepY;

            if (gameState.getPiece(new Position(currX, currY)) != null){
                return false;
            }
        }
        return true;
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
