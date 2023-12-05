package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

import model.Block;
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

    /**
     * A set size for the width and height of a block.
     */
    public static final int SQUARE_SIZE = 40;

    /**
     * A different color red to distinguish the S block from the background.
     */
    public static final Color OTHER_RED = new Color(80, 0, 0);

    /**
     * Used for debugging to ensure no extra panels are instantiated.
     */
    private static int cnt;

    /**
     * The stroke width in pixels.
     */
    private static final int STROKE_WIDTH = 2;

    /**
     * The width for the rectangle.
     */
    private static final int RECTANGLE_WIDTH = 50;

    /**
     * The height for the rectangle.
     */
    private static final int RECTANGLE_HEIGHT = 50;

    private int HEIGHT_COUNTER = 0;

    private int WIDTH_COUNTER = 0;

    /**
     * All the colors the Tetris piece in order of TetrisPieces.values().
     */
    private static final Color[] PIECE_COLORS = {Color.CYAN, Color.ORANGE, Color.BLUE,
            Color.MAGENTA, Color.GREEN,
            Color.YELLOW, OTHER_RED};

    /**
     * The current piece.
     */
    private MovableTetrisPiece myCurrentPiece;

    /**
    * The frozen blocks on the board.
     */
    private List<Block[]> myFrozenBlocks;

    /**
     * Creates the Tetris game panel for the TetrisGUI.
     */
    public GamePanel() {
        super();
        if (cnt > 0) {
            throw new IllegalStateException();
        }
        cnt++;
        this.setPreferredSize(new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE));
        this.setBackground(Color.BLACK);

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
                        (this.getHeight() - SQUARE_SIZE) - k.y() * GamePanel.SQUARE_SIZE,
                        GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
                g2d.setPaint(TetrisPieceColors.getColor(myCurrentPiece));
                g2d.fillRect(k.x() * GamePanel.SQUARE_SIZE + 1,
                        (this.getHeight() - SQUARE_SIZE) - k.y() * GamePanel.SQUARE_SIZE + 1,
                        GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
            }
        }
        if (myFrozenBlocks != null) {
            for (Block[] row: myFrozenBlocks) {
                for (Block b: row) {
                    paintBlock(b, g2d);
                    WIDTH_COUNTER++;
                }
                HEIGHT_COUNTER++;
            }
        }


    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (BoardInterface.CURRENT_PIECE_CHANGING.equals(theEvent.getPropertyName())) {
            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();
        } else if (BoardInterface.FROZEN_CHANGING.equals(theEvent.getPropertyName())) {
            myFrozenBlocks = (List<Block[]>) theEvent.getNewValue();
            repaint();
        }
    }

    private void paintBlock(final Block b, final Graphics2D g2d) {
        if (b != null) {
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(WIDTH_COUNTER * GamePanel.SQUARE_SIZE,
                    (this.getHeight() - SQUARE_SIZE) - HEIGHT_COUNTER * GamePanel.SQUARE_SIZE,
                    GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
            g2d.setPaint(TetrisPieceColors.getColor(b));
            g2d.fillRect(WIDTH_COUNTER * GamePanel.SQUARE_SIZE + 1,
                    (this.getHeight() - SQUARE_SIZE) - HEIGHT_COUNTER * GamePanel.SQUARE_SIZE + 1,
                    GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);

        }

    }

}