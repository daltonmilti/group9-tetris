package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
public final class NextPiecePanel extends JPanel implements PropertyChangeListener {

    /**
     * A set size for the width of a block.
     */
    private static final int TETRIS_PIECE_WIDTH = 4;
    /**
     * A set size for the width of a block.
     */
    private static final int TETRIS_PIECE_HEIGHT = 5;

    /**
     * Keep all other pieces in the center of the screen.
     */
    private static final int X_OFFSET = 140;

    /**
     * Keep all other pieces in the center of the screen.
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
        this.setPreferredSize(
             new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE / 2));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;
        createSquareBorder(g2d);
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

    private void createSquareBorder(final Graphics2D theG2D) {
        final ArrayList<Point> point = new ArrayList<>();
        for (int k = 0; k < (this.getWidth() / GamePanel.SQUARE_SIZE); k++) {
            point.add(new Point(k, 0));
            point.add(new Point(k, (this.getWidth() / GamePanel.SQUARE_SIZE) - 1));
            point.add(new Point(0, k));
            point.add(new Point((this.getWidth() / GamePanel.SQUARE_SIZE) - 1, k));
        }
        for (final Point p : point) {
            theG2D.setPaint(Color.BLACK);
            theG2D.fillRect(p.x() * GamePanel.SQUARE_SIZE, p.y() * GamePanel.SQUARE_SIZE,
                    GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
            theG2D.setPaint(TetrisPieceColors.random());
            theG2D.fillRect(p.x() * GamePanel.SQUARE_SIZE + 1,
                    p.y() * GamePanel.SQUARE_SIZE + 1,
                    GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PropertyChangeMethods.NEXT_PIECE_CHANGE.equals(theEvent.getPropertyName())) {
            myNextPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }
}
