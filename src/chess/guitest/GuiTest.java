package chess.guitest;

import chess.data.GameStateGenerator;
import chess.gui.view.ChessBoardView;

import javax.swing.*;

/**
 * Created by Qing on 2/19/2015.
 */
public class GuiTest {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
        );
        ChessBoardView chessBoardView = new ChessBoardView();
        chessBoardView.update(GameStateGenerator.specialOpening());
        JFrame mainFrame = new JFrame("ChessBoardView");
        mainFrame.setContentPane(chessBoardView.getRootView());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
