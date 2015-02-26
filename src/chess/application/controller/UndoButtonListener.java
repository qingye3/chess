package chess.application.controller;

import chess.application.model.ChessGameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 */
public class UndoButtonListener implements ActionListener {
    ChessGameModel chessGameModel;

    /**
     * Constructor
     * @param chessGameModel the game model that the controller will operate on
     */
    public UndoButtonListener(ChessGameModel chessGameModel) {
        this.chessGameModel = chessGameModel;
    }

    /**
     * invoke the undo command in the Invoker
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        chessGameModel.undo();
    }
}
