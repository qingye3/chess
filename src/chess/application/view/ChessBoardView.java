package chess.application.view;

import chess.application.controller.DragPieceMouseEventHandler;
import chess.application.model.ChessGameModel;
import chess.application.model.Observable;
import chess.lib.constants.Constants;
import chess.lib.data.GameState;
import chess.lib.data.Position;
import chess.lib.datatype.PlayerSide;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Qing on 2/18/2015.
 */
public class ChessBoardView implements Observer {
    private JPanel rootView;
    private JPanel pnlChessBoard;
    private ChessGameModel chessGameModel;
    private JButton resignButton;
    private JButton drawButton;
    private JButton undoButton;

    /**
     * should only be used by the event handler
     * @return a chessboard panel
     */
    public JPanel getPnlChessBoard() {
        return pnlChessBoard;
    }

    /**
     * create a frame/window for the view
     */
    public void createFrame(){
        JFrame mainFrame = new JFrame("ChessBoardView");
        mainFrame.setContentPane(rootView);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Create the chess board
     */
    public ChessBoardView(ChessGameModel chessGameModel) {
        this.chessGameModel = chessGameModel;
        this.chessGameModel.addObserver(this);
        pnlChessBoard.setLayout(new GridLayout(Constants.BOARDHEIGHT, Constants.BOARDWIDTH));
        fillChessBoardWithTIles();
        update(null);
        bindListeners();
    }

    /**
     * say my name
     */
    private void bindListeners() {
        DragPieceMouseEventHandler dragHandler = new DragPieceMouseEventHandler(chessGameModel, this);
        pnlChessBoard.addMouseListener(dragHandler);
        pnlChessBoard.addMouseMotionListener(dragHandler);
    }

    public void repaintChessBoard() {
        for (Component component : pnlChessBoard.getComponents()){
            component.repaint();
        }
    }


    /**
     * update when observable changed. should be called by the observer
     * @param o
     */
    @Override
    public void update(Observable o) {
        updateByGameState(chessGameModel.getCurrentState());
    }


    private void updateByGameState(GameState gameState) {
        for (int i = 0; i < Constants.BOARDHEIGHT * Constants.BOARDWIDTH; i++) {
            Tile tile = (Tile) pnlChessBoard.getComponent(i);
            tile.removeAll();
        }
        addPiecesBySide(gameState, PlayerSide.BLACK);
        addPiecesBySide(gameState, PlayerSide.WHITE);
        rootView.updateUI();
    }

    private void addPiecesBySide(GameState gameState, PlayerSide side) {
        for (Position position : gameState.getPositions(side)) {
            ChessPieceView pieceView = new ChessPieceView(gameState.getPiece(position));
            Tile tile = (Tile) pnlChessBoard.getComponent(getComponetNumberFromPosition(position));
            tile.add(pieceView);
        }
    }

    private int getComponetNumberFromPosition(Position position) {
        int row = Constants.BOARDHEIGHT - position.getY() - 1;
        int column = position.getX();
        return row * Constants.BOARDWIDTH + column;
    }

    private void fillChessBoardWithTIles() {
        for (int i = 0; i < Constants.BOARDHEIGHT; i++) {
            TileType type = getInitialTileTypeByRow(i);
            for (int j = 0; j < Constants.BOARDWIDTH; j++) {
                pnlChessBoard.add(new Tile(type, new Position(j, Constants.BOARDHEIGHT - i - 1)));
                type = type.invertType();
            }
        }
    }

    private TileType getInitialTileTypeByRow(int rowNumber) {
        TileType type;
        if (rowNumber % 2 == 0) {
            type = TileType.LIGHT;
        } else {
            type = TileType.DARK;
        }
        return type;
    }

}
