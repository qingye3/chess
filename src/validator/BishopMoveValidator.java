package validator;

import data.GameState;
import data.Position;
import data.piece.Bishop;
import data.piece.ChessPiece;
import exception.ChessBoardException;
import exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class BishopMoveValidator implements MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Bishop bishop= (Bishop) gameState.getPiece(origin);
        if (origin.equals(destination)){
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture!= null && toCapture.getPlayerSide() == bishop.getPlayerSide()){
            throw new ChessException("Invalid Move: cannot capture your own piece");
        }

        if (!isAligned(origin, destination)){
            throw new ChessException("Invalid Move: Bishop can only move in diagonal direction");
        }

        if (!isPathClear(gameState, origin, destination)){
            throw new ChessBoardException("Invalid Move: Bishop cannot jump over another piece");
        }
    }

    private boolean isAligned(Position origin, Position destination) {
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
}
