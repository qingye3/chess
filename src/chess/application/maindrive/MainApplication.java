package chess.application.maindrive;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import javax.swing.*;

/**
 * Created by Qing on 2/25/2015.
 */
public class MainApplication {
    /**
     * The main driver creating the model and the view
     * exceptions are due to setLookAndFeel
     * @param args
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
        );
        ChessGameModel chessGameModel = new ChessGameModel();
        ChessBoardView chessBoardView = new ChessBoardView(chessGameModel);
        chessBoardView.createFrame();
    }
}
