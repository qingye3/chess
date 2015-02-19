package chess.gui;

import chess.constants.Constants;
import chess.data.GameState;
import chess.data.Position;
import chess.datatype.PlayerSide;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Qing on 2/18/2015.
 */
public class ChessBoardView {
    private JPanel rootView;
    private JPanel pnlChessBoard;

    public JPanel getRootView(){
        return rootView;
    }

    public ChessBoardView() {
        pnlChessBoard.setLayout(new GridLayout(Constants.BOARDHEIGHT, Constants.BOARDWIDTH));
        fillChessBoardWithTIles();
    }


    public void update(GameState gameState){
        for (int i = 0; i < Constants.BOARDHEIGHT*Constants.BOARDWIDTH; i++){
            Tile tile = (Tile) pnlChessBoard.getComponent(i);
            tile.removeAll();
        }
        addPiecesBySide(gameState, PlayerSide.BLACK);
        addPiecesBySide(gameState, PlayerSide.WHITE);
        rootView.updateUI();
    }

    private void addPiecesBySide(GameState gameState, PlayerSide side) {
        for (Position position : gameState.getPositions(side)){
            ChessPieceView pieceView = new ChessPieceView(gameState.getPiece(position));
            Tile tile = (Tile) pnlChessBoard.getComponent(getComponetNumberFromPosition(position));
            tile.add(pieceView);
        }
    }

    private int getComponetNumberFromPosition(Position position){
        int row = Constants.BOARDHEIGHT - position.getY() - 1;
        int column = position.getX();
        return row * Constants.BOARDWIDTH + column;
    }

    private void fillChessBoardWithTIles() {
        for (int i = 0; i < Constants.BOARDHEIGHT; i++){
            TileType type = getInitialTileTypeByRow(i);
            for (int j = 0; j < Constants.BOARDWIDTH; j++){
                pnlChessBoard.add(new Tile(type));
                type = type.invertType();
            }
        }
    }

    private TileType getInitialTileTypeByRow(int rowNumber) {
        TileType type;
        if (rowNumber % 2 == 0){
            type = TileType.LIGHT;
        }else{
            type = TileType.DARK;
        }
        return type;
    }
}
enum TileType{
    DARK{
        @Override
        Color getColor(){
            return new Color(86, 86, 82);
        }
        @Override
        TileType invertType(){
            return LIGHT;
        }
    },
    LIGHT{
        @Override
        Color getColor(){
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

class Tile extends JPanel{
    public Tile(TileType type) {
        super();
        setBackground(type.getColor());
    }
}
