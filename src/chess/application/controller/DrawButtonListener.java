package chess.application.controller;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;
import chess.lib.datatype.GameStatus;
import chess.lib.datatype.PlayerSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 */
public class DrawButtonListener implements ActionListener{
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    public DrawButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerSide currentSide = chessGameModel.getCurrentState().getCurrentSide();
        chessBoardView.showMessage("Players agree to draw");
        chessGameModel.saveResult(GameStatus.STALEMATE);
        chessGameModel.setDraw(true);
        chessGameModel.setNoOneTouchAnyThing(false);
    }
}
