package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Creates the info for the Tetris GUI.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class InfoPanel extends JPanel {

    /**
     * Creates the info panel for the Tetris GUI.
     */
    public InfoPanel() {
        super();
        this.setPreferredSize(
                new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE / 2));
        this.setBackground(Color.GREEN);
    }
}
