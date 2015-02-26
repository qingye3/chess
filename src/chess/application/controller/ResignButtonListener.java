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
public class ResignButtonListener implements ActionListener {
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    public ResignButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerSide currentSide = chessGameModel.getCurrentState().getCurrentSide();
        chessBoardView.showMessage(getPlayerName(currentSide.getOpponentSide()) + " wins");
        chessGameModel.saveResult(getResult(currentSide));
        chessGameModel.setResign(true);
        chessGameModel.setNoOneTouchAnyThing(false);

    }
    private GameStatus getResult(PlayerSide currentSide){
        if (currentSide == PlayerSide.WHITE){
            return GameStatus.BLACKWINS;
        } else {
            return GameStatus.WHITEWINS;
        }

    }

    private String getPlayerName(PlayerSide side){
        if (side == PlayerSide.WHITE){
            return "White";
        } else {
            return "Black";
        }

    }
}
