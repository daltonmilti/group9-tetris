package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
        if (myGhostPiece != null && myCurrentPiece != null) {
            paintPiece(g2d, myGhostPiece, Color.GRAY);
            paintPiece(g2d, myCurrentPiece, TetrisPieceColors.getColor(myCurrentPiece));
        }
        if (myFrozenBlocks != null) {
            for (int i = 0; i < myFrozenBlocks.size(); i++) {
                for (int k = 0; k < myFrozenBlocks.get(0).length; k++) {
                    paintBlock(myFrozenBlocks.get(i)[k], g2d, i, k);
                }
            }
        }
        if (myGamePaused) {
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, PAUSED_FONT_SIZE));
            g2d.setPaint(Color.WHITE);
            g2d.drawString("PAUSED", PAUSED_POINT.x(), PAUSED_POINT.y());

        }
        if (myGameOver) {
            //background
            g2d.setPaint(Color.WHITE); // this is just to see it's in the right place
            g2d.fillRect(0, 350, 400, 100);

            //font
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, GAME_OVER_FONT_SIZE));
            g2d.setPaint(Color.RED);
            g2d.drawString("GAME OVER", PAUSED_POINT.x(), PAUSED_POINT.y());
        }
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
            if (myGhostPiece != null) {
                final Point[] p = myGhostPiece.getBoardPoints();
                for (final Point k : p) {
                    if (myFrozenBlocks.get(k.y())[k.x()] == null) {
                        myGhostPiece = null;
                        break;
                    }
                }
            }
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
        }
    }
}