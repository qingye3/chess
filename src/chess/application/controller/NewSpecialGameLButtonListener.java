package chess.application.controller;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 */
public class NewSpecialGameLButtonListener implements ActionListener {
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    public NewSpecialGameLButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!chessGameModel.isNoOneTouchAnyThing() && chessGameModel.isPlayable()){
            chessBoardView.showMessage("Cannot start a new game while a game is in progress");
            return;
        }
        chessGameModel.initializeSpecialOpening();
    }
}
