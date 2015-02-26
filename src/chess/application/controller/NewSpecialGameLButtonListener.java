package chess.application.controller;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 * Listens to New Special Game button click
 * Only allows new game to be created when the current game is over
 */
public class NewSpecialGameLButtonListener implements ActionListener {
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    /**
     * @param chessGameModel model to operate on
     * @param chessBoardView view creating the listener
     */
    public NewSpecialGameLButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    /**
     * response to new special game button click
     * initialize a new special game with special pieces
     * shows an error message if the current game is in progress
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!chessGameModel.isNoOneTouchAnyThing() && chessGameModel.isPlayable()){
            chessBoardView.showMessage("Cannot start a new game while a game is in progress");
            return;
        }
        chessGameModel.initializeSpecialOpening();
    }
}
