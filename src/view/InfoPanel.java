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

    /** Used for debugging to ensure no extra panels are instantiated. */
    private static int cnt;

    /**
     * Creates the info panel for the Tetris GUI.
     */
    public InfoPanel() {
        super();
        if (cnt > 0) {
            throw new IllegalStateException();
        }
        cnt++;
        this.setPreferredSize(
                new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE / 2));
        this.setBackground(Color.GREEN);
    }
}
