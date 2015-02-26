package chess.application.controller;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 * Listener for the new game button
 */
public class NewGameButtonListener implements ActionListener{
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    /**
     *
     * @param chessGameModel model to operate on
     * @param chessBoardView view creating the listener
     */
    public NewGameButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    /**
     * response to button click
     * shows an error message if the current game is still in progress
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (chessGameModel.isPlayable()){
            chessBoardView.showMessage("Cannot start a new game while a game is in progress");
            return;
        }
        chessGameModel.initializeStandardOpening();
    }
}
