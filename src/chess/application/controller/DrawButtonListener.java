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

    /**
     * Listen to the Draw button
     * @param chessGameModel model to operate on
     * @param chessBoardView view creating the listener
     */
    public DrawButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    /**
     * response to button click
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (! chessGameModel.isPlayable()){
            chessBoardView.showMessage("Game already over. Cannot draw.");
            return;
        }
        chessBoardView.showMessage("Players agree to draw");
        chessGameModel.updateScoreByGameResult(GameStatus.STALEMATE);
        chessGameModel.setDraw(true);
        chessGameModel.setNoOneTouchAnyThing(false);
    }
}
