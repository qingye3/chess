package chess.application.view;


import chess.lib.data.Position;

import javax.swing.*;

/**
 * Created by Qing on 2/26/2015.
 */
public class Tile extends JPanel {
    Position position;
    public Tile(TileType type, Position position) {
        super();
        setBackground(type.getColor());
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
