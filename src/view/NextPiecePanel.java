package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import model.BoardInterface;
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
public final class NextPiecePanel extends JPanel implements PropertyChangeListener {

    /** Used for debugging to ensure no extra panels are instantiated. */
    private static int cnt;

    /**
     * Keeping the T piece in the middle.
     * This is placeholder value, will later be calculated
     * using a PropertyChangeListener and a simple algorithm.
     */
    private static final int X_OFFSET = 140;

    /**
     * Keeping the T piece in the middle.
     * This is placeholder value, will later be calculated
     * using a PropertyChangeListener and a simple algorithm.
     */
    private static final int Y_OFFSET = 120;

    /**
     * The next piece.
     */
    private TetrisPiece myNextPiece;

    /**
     * Creates the next piece panel for the Tetris GUI.
     */
    public NextPiecePanel() {
        super();
        if (cnt > 0) {
            throw new IllegalStateException();
        }
        cnt++;
        this.setPreferredSize(
             new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE / 2));
        this.setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;

        if (myNextPiece != null) {
            final Point[] i = myNextPiece.getPoints();
            for (final Point k : i) {
                g2d.setPaint(Color.BLACK);
                g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + X_OFFSET,
                        k.y() * GamePanel.SQUARE_SIZE + Y_OFFSET,
                        GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
                g2d.setPaint(Color.MAGENTA);
                g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + X_OFFSET + 1,
                        k.y() * GamePanel.SQUARE_SIZE + Y_OFFSET + 1,
                        GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
            }
        }
    }
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (BoardInterface.NEXT_PIECE_CHANGE.equals(theEvent.getPropertyName())) {
            myNextPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }
}
