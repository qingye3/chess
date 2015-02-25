package chess.gui.view;


import chess.data.piece.ChessPiece;
import chess.datatype.PlayerSide;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Qing on 2/18/2015.
 */
public class ChessPieceView extends JLabel{
    private static final Map<String, String> blackPathDictionary;
    private static final Map<String, String> whitePathDictionary;
    static {
        blackPathDictionary= new HashMap<String, String>();
        whitePathDictionary= new HashMap<String, String>();
        blackPathDictionary.put("" , "static/img/ChessPieces/bp.png");
        blackPathDictionary.put("K", "static/img/ChessPieces/bk.png");
        blackPathDictionary.put("B", "static/img/ChessPieces/bb.png");
        blackPathDictionary.put("N", "static/img/ChessPieces/bn.png");
        blackPathDictionary.put("R", "static/img/ChessPieces/br.png");
        blackPathDictionary.put("Q", "static/img/ChessPieces/bq.png");
        blackPathDictionary.put("RN", "static/img/ChessPieces/brn.png");
        blackPathDictionary.put("NB", "static/img/ChessPieces/bnb.png");
        whitePathDictionary.put("" , "static/img/ChessPieces/wp.png");
        whitePathDictionary.put("K", "static/img/ChessPieces/wk.png");
        whitePathDictionary.put("B", "static/img/ChessPieces/wb.png");
        whitePathDictionary.put("N", "static/img/ChessPieces/wn.png");
        whitePathDictionary.put("R", "static/img/ChessPieces/wr.png");
        whitePathDictionary.put("Q", "static/img/ChessPieces/wq.png");
        whitePathDictionary.put("RN", "static/img/ChessPieces/wrn.png");
        whitePathDictionary.put("NB", "static/img/ChessPieces/wnb.png");
    }
    public ChessPieceView(ChessPiece chessPiece) {
        super(getIconByPiece(chessPiece));
    }

    private static ImageIcon getIconByPiece(ChessPiece chessPiece) {
        if (chessPiece.getPlayerSide() == PlayerSide.BLACK){
            return new ImageIcon(blackPathDictionary.get(chessPiece.toString()));

        } else {
            return new ImageIcon(whitePathDictionary.get(chessPiece.toString()));
        }
    }
}
