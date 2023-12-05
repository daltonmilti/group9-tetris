package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import model.BoardInterface;
import model.MovableTetrisPiece;
import model.Point;

/**
 * Creates the game portion of the Tetris GUI.
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class GamePanel extends JPanel implements PropertyChangeListener {

    /** A set size for the width and height of a block. */
    public static final int SQUARE_SIZE = 40;

    /**
     * A different color red to distinguish the S block from the background.
     */
    public static final Color OTHER_RED = new Color(80, 0, 0);

    /** Used for debugging to ensure no extra panels are instantiated. */
    private static int cnt;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 2;

    /** The width for the rectangle. */
    private static final int RECTANGLE_WIDTH = 50;

    /** The height for the rectangle. */
    private static final int RECTANGLE_HEIGHT = 50;

    /**
     * All the colors the Tetris piece in order of TetrisPieces.values().
     */
    private static final Color[] PIECE_COLORS = {Color.CYAN, Color.ORANGE, Color.BLUE,
                                                 Color.MAGENTA, Color.GREEN,
                                                 Color.YELLOW, OTHER_RED};

    /** The current piece. */
    private MovableTetrisPiece myCurrentPiece;

    /** Creates the Tetris game panel for the TetrisGUI. */
    public GamePanel() {
        super();
        if (cnt > 0) {
            throw new IllegalStateException();
        }
        cnt++;
        this.setPreferredSize(new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE));
        this.setBackground(Color.RED);
    }

    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;

        if (myCurrentPiece != null) {
            final Point[] i = myCurrentPiece.getBoardPoints();
            for (final Point k : i) {
                g2d.setPaint(Color.BLACK);
                g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE,
                        k.y() * GamePanel.SQUARE_SIZE,
                        GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
                g2d.setPaint(Color.MAGENTA);
                g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + 1,
                        k.y() * GamePanel.SQUARE_SIZE + 1,
                        GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (BoardInterface.CURRENT_PIECE_CHANGING.equals(theEvent.getPropertyName())) {
            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }
}