package data.piece;

import controller.MoveController;
import data.Position;

/**
 * Created by Qing on 2/10/2015.
 * This class represents a chess piece. getMover
 */
public abstract class ChessPiece {
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = new Position(position);
    }
    public abstract MoveController getMoveController();
    public abstract String toString();
}
