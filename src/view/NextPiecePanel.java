package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.Point;
import model.TetrisPiece;

/**
 * Creates the next piece panel for the Tetris GUI.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class NextPiecePanel extends JPanel {

    /**
     * Keeping the T piece in the middle.
     * This is place holder value, will later be calculated
     * using a PropertyChangeListener and a simple algorithm.
     */
    private static final int X_OFFSET = 140;

    /**
     * Keeping the T piece in the middle.
     * This is place holder value, will later be calculated
     * using a PropertyChangeListener and a simple algorithm.
     */
    private static final int Y_OFFSET = 120;

    /**
     * Creates the next piece panel for the Tetris GUI.
     */
    public NextPiecePanel() {
        super();
        this.setPreferredSize(
             new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE / 2));
        this.setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;

        final Point[] i = TetrisPiece.T.getPoints();
        for (final Point k : i) {
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + X_OFFSET,
                         k.y() * GamePanel.SQUARE_SIZE + Y_OFFSET,
                            GamePanel.SQUARE_SIZE, GamePanel.SQUARE_SIZE);
            g2d.setPaint(Color.MAGENTA);
            g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + X_OFFSET + 1,
                         k.y() * GamePanel.SQUARE_SIZE + Y_OFFSET + 1,
                      GamePanel.SQUARE_SIZE - 2, GamePanel.SQUARE_SIZE - 2);
        }
    }
}
