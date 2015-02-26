package chess.application.controller;


import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 */
public class ShowScoreButtonListener implements ActionListener{
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    public ShowScoreButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chessBoardView.showMessage(getMessage());
    }
    String getMessage(){
        String mesage = "";
        mesage += "White Score: " + chessGameModel.getWhiteScore();
        mesage += "\n";
        mesage += "Black Score: " + chessGameModel.getBlackScore();
        return mesage;
    }
}
