package chess.application.controller;


import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 * Listener for the score button
 */
public class ShowScoreButtonListener implements ActionListener{
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;


    /**
     * Constructor
     * @param chessGameModel the game model that the controller will operate on
     * @param chessBoardView the view to be notified
     */
    public ShowScoreButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    /**
     * notify the view to show a score board using info from the model
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        chessBoardView.showMessage(getMessage());
    }

    /**
     * compose a score board message using the scores
     * @return the message
     */
    String getMessage(){
        String mesage = "";
        mesage += "White Score: " + chessGameModel.getWhiteScore();
        mesage += "\n";
        mesage += "Black Score: " + chessGameModel.getBlackScore();
        return mesage;
    }
}
