package chess.application.controller;

import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;
import chess.lib.datatype.GameStatus;
import chess.lib.datatype.PlayerSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Qing on 2/26/2015.
 * Listener for the resign button
 * A resign cannot be undoone and the side clicking the resign button lose immediately
 */
public class ResignButtonListener implements ActionListener {
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;

    /**
     * Constructor
     * @param chessGameModel model to operate on
     * @param chessBoardView view creating the listener
     */
    public ResignButtonListener(ChessGameModel chessGameModel, ChessBoardView chessBoardView) {
        this.chessGameModel = chessGameModel;
        this.chessBoardView = chessBoardView;
    }

    /**
     * Response to a resign button click
     * Shows an opponent wins message and update the score board
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (! chessGameModel.isPlayable()){
            chessBoardView.showMessage("Game already over. Cannot resign.");
            return;
        }
        PlayerSide currentSide = chessGameModel.getCurrentState().getCurrentSide();
        chessBoardView.showMessage(getPlayerName(currentSide.getOpponentSide()) + " wins");
        chessGameModel.updateScoreByGameResult(getResult(currentSide));
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
