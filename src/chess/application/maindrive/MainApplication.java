package chess.application.maindrive;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import javax.swing.*;

/**
 * Created by Qing on 2/25/2015.
 */
public class MainApplication {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
        );
        ChessGameModel chessGameModel = new ChessGameModel();
        ChessBoardView chessBoardView = new ChessBoardView(chessGameModel);
        chessBoardView.createFrame();
    }
}
