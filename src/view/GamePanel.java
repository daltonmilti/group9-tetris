package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import model.Block;
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
     * Point on the screen of the PAUSED logo.
     */
    private static final Point PAUSED_POINT = new Point(55, 415);

    /**
     * GAME OVER logo font size.
     */
    private static final int GAME_OVER_FONT_SIZE = 50;

    /**
     * PAUSED logo font size.
     */
    private static final int PAUSED_FONT_SIZE = 70;

    /**
     * Used to represent Times New Roman font.
     */
    private static final String TEXT_ROMAN = "Times New Roman";

    /**
     * Game Over Specifications
     */
    private static final int[] GAME_OVER_SPECS = {350, 400, 100};

    /**
     * Color Green for Border
     */
    private static final Color GREEN_BORDER = new Color(57, 255, 20);

    /**
     * Color Red for Border
     */
    private static final Color RED_BORDER = new Color(255, 20, 57);

    /**
     * Color Blue for Border
     */
    private static final Color BLUE_BORDER = new Color(20, 57, 255);

    /**
     * Number of Rows
     */
    private static final int ROWS = 20;

    /**
     * Number of Columns
     */
    private static final int COLUMNS = 10;

    /**
     * Screen Size
     */
    private static final int SIZE = 800;

    /**
     * The current piece.
     */
    private MovableTetrisPiece myCurrentPiece;

    /**
     * The ghost piece.
     */
    private MovableTetrisPiece myGhostPiece;

    /**
    * The frozen blocks on the board.
     */
    private List<Block[]> myFrozenBlocks;

    /**
     * Whether or not the game is paused.
     */
    private boolean myGamePaused;

    /**
     * Indicator for Hard Mode
     */
    private boolean myHardMode;

    /**
     * Whether or not the game is over.
     */
    private boolean myGameOver;

    /**
     * Creates the Tetris game panel for the TetrisGUI.
     */
    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        paintGrid(g2d);
        if (myGhostPiece != null && myCurrentPiece != null) {
            paintPiece(g2d, myGhostPiece, Color.GRAY);
            paintPiece(g2d, myCurrentPiece, TetrisPieceColors.getColor(myCurrentPiece));

        }
        if (myFrozenBlocks != null && !myHardMode) {
            for (int i = 0; i < myFrozenBlocks.size(); i++) {
                for (int k = 0; k < myFrozenBlocks.get(0).length; k++) {
                    paintBlock(myFrozenBlocks.get(i)[k], g2d, i, k);
                }
            }
        }
        if (myGamePaused) {
            paintGamePaused(g2d);
        }
        if (myGameOver) {
            paintGameOver(g2d);
        }

    }

    private void paintGameOver(final Graphics2D theG2D) {
        //background
        theG2D.setPaint(Color.GRAY);
        theG2D.fillRect(0, GAME_OVER_SPECS[0], GAME_OVER_SPECS[1], GAME_OVER_SPECS[2]);

        //font
        theG2D.setFont(new Font(TEXT_ROMAN, Font.PLAIN, GAME_OVER_FONT_SIZE));
        theG2D.setPaint(Color.RED);
        theG2D.drawString("GAME OVER", PAUSED_POINT.x(), PAUSED_POINT.y());
    }

    private void paintGamePaused(final Graphics2D theG2D) {
        theG2D.setPaint(Color.BLACK);
        theG2D.setFont(new Font(TEXT_ROMAN, Font.PLAIN, PAUSED_FONT_SIZE));
        theG2D.setPaint(Color.WHITE);
        theG2D.drawString("PAUSED", PAUSED_POINT.x(), PAUSED_POINT.y());
    }

    private void paintPiece(final Graphics2D theG2D,
                            final MovableTetrisPiece thePiece,
                            final Color theColor) {
        final Point[] p = thePiece.getBoardPoints();
        for (final Point k : p) {
            theG2D.setPaint(Color.BLACK);
            theG2D.fillRect(k.x() * GamePanel.SQUARE_SIZE,
                    (this.getHeight() - SQUARE_SIZE) - k.y() * GamePanel.SQUARE_SIZE,
                    GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
            theG2D.setPaint(theColor);
            theG2D.fillRect(k.x() * GamePanel.SQUARE_SIZE + 1,
                    (this.getHeight() - SQUARE_SIZE) - k.y() * GamePanel.SQUARE_SIZE + 1,
                    GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);
        }
    }

    private void paintGrid(final Graphics2D theG2D) {
        final ArrayList<Point> p = new ArrayList<>();
        for (int x = 0; x < COLUMNS; x++) {
            theG2D.setPaint(TetrisPieceColors.random());
            theG2D.drawLine(x * SQUARE_SIZE, SIZE, x * SQUARE_SIZE, 0);
        }
        for (int y = 0; y < ROWS; y++) {
            theG2D.setPaint(TetrisPieceColors.random());
            theG2D.drawLine(0, y * SQUARE_SIZE, SIZE / 2, y * SQUARE_SIZE);
        }
    }

    private void paintBlock(final Block theBlock, final Graphics2D theG2d,
                            final int theX, final int theY) {
        if (theBlock != null) {
            theG2d.setPaint(Color.BLACK);
            theG2d.fillRect(theY * GamePanel.SQUARE_SIZE,
                    (this.getHeight() - SQUARE_SIZE) - theX * GamePanel.SQUARE_SIZE,
                    GamePanel.SQUARE_SIZE + 1, GamePanel.SQUARE_SIZE + 1);
            theG2d.setPaint(TetrisPieceColors.getColor(theBlock));
            theG2d.fillRect(theY * GamePanel.SQUARE_SIZE + 1,
                    (this.getHeight() - SQUARE_SIZE) - theX * GamePanel.SQUARE_SIZE + 1,
                    GamePanel.SQUARE_SIZE - 1, GamePanel.SQUARE_SIZE - 1);

        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PropertyChangeMethods.CURRENT_PIECE_CHANGING.equals(theEvent.getPropertyName())) {
            myCurrentPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();
        } else if (PropertyChangeMethods.FROZEN_CHANGING.equals(theEvent.getPropertyName())) {
            myFrozenBlocks = (LinkedList<Block[]>) theEvent.getNewValue();
            repaint();
        } else if (PropertyChangeMethods.GAME_PAUSED.equals(theEvent.getPropertyName())) {
            myGamePaused = (boolean) theEvent.getNewValue();
            repaint();
        } else if (PropertyChangeMethods.GHOST_PIECE_CHANGING.
                   equals(theEvent.getPropertyName())) {
            myGhostPiece = (MovableTetrisPiece) theEvent.getNewValue();
            repaint();
        } else if (PropertyChangeMethods.GAME_END.equals(theEvent.getPropertyName())) {
            myGameOver = (boolean) theEvent.getNewValue();
            repaint();
        } else if (PropertyChangeMethods.HARD_MODE.equals(theEvent.getPropertyName())) {
            myHardMode = (boolean) theEvent.getNewValue();
            repaint();
        }
    }
}