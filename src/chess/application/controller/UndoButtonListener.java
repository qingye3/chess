package chess.application.controller;

import chess.application.model.ChessGameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 */
public class UndoButtonListener implements ActionListener {
    ChessGameModel chessGameModel;

    public UndoButtonListener(ChessGameModel chessGameModel) {
        this.chessGameModel = chessGameModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chessGameModel.undo();
    }
}
