package chess.application.view;

import java.awt.*;

/**
 * Created by Qing on 2/26/2015.
 */
public enum TileType {
    DARK {
        @Override
        Color getColor() {
            return new Color(86, 86, 82);
        }

        @Override
        TileType invertType() {
            return LIGHT;
        }
    },
    LIGHT {
        @Override
        Color getColor() {
            return new Color(225, 227, 232);
        }

        @Override
        TileType invertType() {
            return DARK;
        }

    };

    abstract Color getColor();

    abstract TileType invertType();
}
