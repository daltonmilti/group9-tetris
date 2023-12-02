package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import model.Board;
import model.BoardInterface;
import model.Point;
import model.TetrisPiece;

import static model.PropertyChangeBoard.CURRENT_PIECE_CHANGING;

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

    /** A different color orange. */
    public static final Color ORANGE = new Color(255, 165, 0);

    /**
     * A different color red to distinguish the S block from the background.
     */
    public static final Color OTHER_RED = new Color(80, 0, 0);

    /** Y offset for the I block. */
    public static final int I_SPACER = -2;

    /** Y offset for the L block. */
    public static final int L_SPACER = 1;

    /** Y offset for the J block. */
    public static final int J_SPACER = 4;

    /** Y offset for the O block. */
    public static final int O_SPACER = 7;

    /** Y offset for the Z block. */
    public static final int Z_SPACER = 10;

    /** Y offset for the S block. */
    public static final int S_SPACER = 13;

    /** Y offset for the T block. */
    public static final int T_SPACER = 16;

    /** Used for debugging */
    private static int cnt;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 2;

    /** The width for the rectangle. */
    private static final int RECTANGLE_WIDTH = 50;

    /** The height for the rectangle. */
    private static final int RECTANGLE_HEIGHT = 50;

    /** The current piece */
    private TetrisPiece myCurrentPiece;

    /**
     * Creates the game panel for the Tetris GUI.
     */
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
        final TetrisPiece[] allTetrisPieces = TetrisPiece.values();
        final Color[] allColors = {Color.CYAN, ORANGE, Color.BLUE, Color.YELLOW,
                                   OTHER_RED, Color.MAGENTA, Color.GREEN};
        final int[] spacing = {I_SPACER, L_SPACER, J_SPACER, O_SPACER,
                               Z_SPACER, S_SPACER, T_SPACER};

        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;

        for (int i = 0; i < allTetrisPieces.length; i++) {
            final Point[] k = allTetrisPieces[i].getPoints();
            for (final Point p : k) {
                makeBlock(g2d, p.x(), p.y(), allColors[i], spacing[i]);
            }
        }
//        g2d.setPaint(Color.BLACK);
//        for (int i = 0; i < TetrisGUI.SIZE / (SQUARE_SIZE * 2); i++) {
//            for (int k = 0; k < TetrisGUI.SIZE / SQUARE_SIZE; k++) {
//                g2d.drawRect(i * SQUARE_SIZE, k * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
//            }
//        }
    }
    private void makeBlock(final Graphics2D theG2D, final int theX, final int theY,
                           final Color theColor, final int theSpacer) {
        final int x = theX * SQUARE_SIZE;
        final int y = theY * SQUARE_SIZE + theSpacer * SQUARE_SIZE;
        theG2D.setPaint(Color.BLACK);
        theG2D.fillRect(x, y, SQUARE_SIZE + 1, SQUARE_SIZE + 1);
        theG2D.setPaint(theColor);
        theG2D.fillRect(x + 1, y + 1, SQUARE_SIZE - 1, SQUARE_SIZE - 1);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (CURRENT_PIECE_CHANGING.equals(theEvent.getPropertyName())) {
            myCurrentPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }
}
