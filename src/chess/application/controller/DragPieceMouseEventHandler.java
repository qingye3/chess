package chess.application.controller;

import chess.application.command.Command;
import chess.application.command.MovePieceCommand;
import chess.application.model.ChessGameModel;
import chess.application.view.ChessBoardView;
import chess.application.view.ChessPieceView;
import chess.application.view.Tile;
import chess.lib.data.Position;
import chess.lib.datatype.PlayerSide;
import chess.lib.exception.ChessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Qing on 2/26/2015.
 * Drag piece event handler
 * Dragging a piece will fire a mouse pressed when left button is pressed. Mouse dragged will be
 * fired when the mouse is dragging the piece. Mouse released will be fired when the piece is dropped.
 *
 * The handler will issue a move piece command to the model. But execution is up to the model. If the
 * execution is interrupted by an exception, the piece will be reset to its original position.
 */
public class DragPieceMouseEventHandler implements MouseListener, MouseMotionListener {
    ChessGameModel chessGameModel;
    ChessBoardView chessBoardView;
    JPanel pnlChessBoard;
    ChessPieceView pickedUpPiece;
    Tile initialTile;

    /**
     * Created a handler.
     * @param chessGameModel the game model that the handler wants to modify
     * @param chessBoardView the view the handler that wants to modify/get input from
     */
    public DragPieceMouseEventHandler(ChessGameModel chessGameModel, ChessBoardView chessBoardView){
        this.chessGameModel = chessGameModel;
        pnlChessBoard = chessBoardView.getPnlChessBoard();
        this.chessBoardView = chessBoardView;
        pickedUpPiece = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component component = pnlChessBoard.findComponentAt(e.getX(), e.getY());
        if (component instanceof ChessPieceView){
            pickedUpPiece = (ChessPieceView) component;
            initialTile = (Tile) pickedUpPiece.getParent();
        } else{
            pickedUpPiece = null;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pickedUpPiece == null){
            return;
        }
        Position initialPosition = initialTile.getPosition();
        Tile pieceAtTile = (Tile) pickedUpPiece.getParent();
        Position finalPosition = pieceAtTile.getPosition();
        Command cmd = new MovePieceCommand(chessGameModel, initialPosition, finalPosition);
        try {
            System.out.println(chessGameModel);
            chessGameModel.storeAndExecute(cmd);
        } catch (ChessException exception) {
            exception.printStackTrace();
            pieceAtTile.remove(pickedUpPiece);
            initialTile.add(pickedUpPiece);
            chessBoardView.repaintChessBoard();
            return;
        }
        pieceAtTile.removeAll();
        pieceAtTile.add(pickedUpPiece);
        chessBoardView.repaintChessBoard();
        pickedUpPiece = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (pickedUpPiece != null){
            Component component = pnlChessBoard.findComponentAt(e.getX(), e.getY());
            if (component instanceof JPanel){
                JPanel currentTile = (JPanel) component;
                JPanel pieceAtTile = (JPanel) pickedUpPiece.getParent();
                System.out.println(pieceAtTile);
                if (pieceAtTile != null && currentTile != pieceAtTile){
                    pieceAtTile.remove(pickedUpPiece);
                    currentTile.add(pickedUpPiece, 0);
                    chessBoardView.repaintChessBoard();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
