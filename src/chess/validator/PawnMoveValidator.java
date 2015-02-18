package chess.validator;

import chess.data.GameState;
import chess.data.Position;
import chess.data.piece.ChessPiece;
import chess.data.piece.Pawn;
import chess.datatype.PlayerSide;
import chess.exception.ChessException;

import static java.lang.Math.abs;

/**
 * Created by Qing on 2/11/2015.
 */
public class PawnMoveValidator extends MoveValidator {
    private PositionValidator positionValidator = new PositionValidator();

    /**
     * Check if the movement of the Pawn is valid does not check the origin
     * @param gameState original state
     * @param origin which piece to move
     * @param destination move to where
     * @throws ChessException
     */
    @Override
    public void validate(GameState gameState, Position origin, Position destination) throws ChessException {
        positionValidator.validate(destination);
        Pawn pawn = (Pawn) gameState.getPiece(origin);

        if (origin.equals(destination)) {
            throw new ChessException("Invalid Move: no displacement");
        }

        ChessPiece toCapture = gameState.getPiece(destination);
        if (toCapture != null) {
            if (toCapture.getPlayerSide() == pawn.getPlayerSide()) {
                throw new ChessException("Invalid Move: cannot capture your own piece");
            }
            validateCaptureMove(origin, gameState, destination, pawn);
        } else {
            validateNormalMove(gameState, origin, destination, pawn);
        }

    }

    private void validateNormalMove(GameState gameState, Position origin, Position destination, Pawn pawn) throws ChessException {
        if (destination.getX() != origin.getX())
            throw new ChessException("Invalid Move: pawn cannot move aside");

        if (pawn.getPlayerSide() == PlayerSide.WHITE) {
            validateWhiteNormalMove(gameState, origin, destination);
        } else {
            validateBlackNormalMove(gameState, origin, destination);
        }
    }

    private boolean noPieceAt(GameState gameState, Position position){
        ChessPiece cp = gameState.getPiece(position);
        return cp == null;
    }

    private void validateBlackNormalMove(GameState gameState, Position origin, Position destination) throws ChessException {
        //Black pawn in line 7 (or 6 in 0 prefix array) can move two step forward
        if (origin.getY() == 6){
            if (destination.getY() == 5 &&
                    noPieceAt(gameState, new Position(origin.getX(), 5))){
                return;
            }
            if (destination.getY() == 4 &&
                    noPieceAt(gameState, new Position(origin.getX(), 4)) &&
                    noPieceAt(gameState, new Position(origin.getX(), 5))){
                return;
            }
        } else {
            if (destination.getY() == origin.getY() - 1 &&
                    noPieceAt(gameState, new Position(origin.getX(), destination.getY()))){
                return;
            }
        }
        throw new ChessException("Invalid Move: you must move the pawn as if it is a pawn");
    }

    private void validateWhiteNormalMove(GameState gameState, Position origin, Position destination) throws ChessException {
        //Black pawn in line 2 (or 1 in 0 prefix array) can move two step forward
        if (origin.getY() == 1){
            if (destination.getY() == 2 &&
                    noPieceAt(gameState, new Position(origin.getX(), 3))){
                return;
            }
            if (destination.getY() == 3 &&
                    noPieceAt(gameState, new Position(origin.getX(), 2)) &&
                    noPieceAt(gameState, new Position(origin.getX(), 3))){
                return;
            }
        } else {
            if (destination.getY() == origin.getY() + 1 &&
                    noPieceAt(gameState, new Position(origin.getX(), destination.getY()))){
                return;
            }
        }
        throw new ChessException("Invalid Move: you must move the pawn as if it is a pawn");

    }

    private void validateCaptureMove(Position origin, GameState gameState, Position destination, Pawn pawn)
            throws ChessException {

        if (pawn.getPlayerSide() == PlayerSide.WHITE) {
            validateWhiteCaptureMove(gameState, origin, destination);
        } else {
            validateBlackCaptureMove(gameState, origin, destination);
        }
    }

    private void validateBlackCaptureMove(GameState gameState, Position origin, Position destination) throws ChessException {
        if (destination.getY() - origin.getY() != -1 ||
                getDisplacementInX(origin, destination) != 1){
            throw new ChessException("Invalid Move: pawn must capture pieces diagonally ahead");
        }

    }

    private void validateWhiteCaptureMove(GameState gameState, Position origin, Position destination) throws ChessException {
        if (destination.getY() - origin.getY() != 1 ||
                getDisplacementInX(origin, destination) != 1){
            throw new ChessException("Invalid Move: pawn must capture pieces diagonally ahead");
        }
    }

}
