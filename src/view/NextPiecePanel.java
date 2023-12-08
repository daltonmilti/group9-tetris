package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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

    /**
     * A set size for the width of a block.
     */
    private static final int TETRIS_PIECE_WIDTH = 4;
    /**
     * A set size for the width of a block.
     */
    private static final int TETRIS_PIECE_HEIGHT = 5;

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
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;
        final ArrayList<Point> point = new ArrayList<>();
        for (int k = 0; k < 10; k++) {
            point.add(new Point(k, 0));
            point.add(new Point(k, 9));
            point.add(new Point(0, k));
            point.add(new Point(9, k));
        }
        for (final Point p : point) {
            g2d.setPaint(Color.DARK_GRAY);
            g2d.fillRect(p.x() * GamePanel.SQUARE_SIZE, p.y() * GamePanel.SQUARE_SIZE,
                    GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
            g2d.setPaint(Color.GRAY);
            g2d.fillRect(p.x() * GamePanel.SQUARE_SIZE + 1,
                    p.y() * GamePanel.SQUARE_SIZE + 1,
                    GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
        }
        if (myNextPiece != null) {
            final Point[] i = myNextPiece.getPoints();
            for (final Point k : i) {
                g2d.setPaint(TetrisPieceColors.getColor(myNextPiece));
                if (myNextPiece == TetrisPiece.O) {
                    g2d.fillRect(k.y() * GamePanel.SQUARE_SIZE + (getWidth()
                                    - TETRIS_PIECE_WIDTH * GamePanel.SQUARE_SIZE) / 2,
                            k.x() * GamePanel.SQUARE_SIZE + (getHeight() - TETRIS_PIECE_WIDTH
                                    * GamePanel.SQUARE_SIZE) / 2,
                            GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
                } else if (myNextPiece == TetrisPiece.I) {
                    g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + (getWidth()
                                    - TETRIS_PIECE_WIDTH * GamePanel.SQUARE_SIZE) / 2,
                            k.y() * GamePanel.SQUARE_SIZE + (getHeight()
                                    - TETRIS_PIECE_HEIGHT * GamePanel.SQUARE_SIZE) / 2,
                            GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
                } else {
                    g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + X_OFFSET + 1,
                            k.y() * GamePanel.SQUARE_SIZE + Y_OFFSET + 1,
                            GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
                }
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
