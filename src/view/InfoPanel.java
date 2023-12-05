package view;

import model.BoardInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Graphics;
import javax.swing.*;

/**
 * Creates the info for the Tetris GUI.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class InfoPanel extends JPanel implements PropertyChangeListener {

    /** The number of rows to progress to the next level. */
    private static final int ROWS_TO_PROGRESS = 5;

    /** One line cleared. */
    private static final int ONE_LINE = 1;

    /** One line cleared. */
    private static final int TWO_LINE = 2;

    /** One line cleared. */
    private static final int THREE_LINE = 3;

    /** One line cleared. */
    private static final int FOUR_LINE = 4;

    /** A constant used to find score when the user clears one line */
    private static final int ONE_LINE_CLEARED = 40;

    /** A constant used to find score when the user clears two lines */
    private static final int TWO_LINE_CLEARED = 100;

    /** A constant used to find score when the user clears three lines */
    private static final int THREE_LINE_CLEARED = 300;

    /** A constant used to find score when the user clears four lines */
    private static final int FOUR_LINE_CLEARED = 1200;

    /** Used for debugging to ensure no extra panels are instantiated. */
    private static int cnt;

    /** The amount of cleared rows in the current game. */
    private int myClearedRows;

    /** The current level the user is on */
    private int myLevel;

    /** The users current score */
    private int myScore;

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
        this.setBackground(Color.WHITE);
    }

    private void display(final int theRowsCleared) {
        myLevel = (myClearedRows / ROWS_TO_PROGRESS) + 1;
        myScore += calculateScore(theRowsCleared);
        this.repaint();
    }

    private int calculateScore(final int theRowsCleared) {
        return switch (theRowsCleared) {
            case ONE_LINE -> myLevel * ONE_LINE_CLEARED;
            case TWO_LINE -> myLevel * TWO_LINE_CLEARED;
            case THREE_LINE -> myLevel * THREE_LINE_CLEARED;
            case FOUR_LINE -> myLevel * FOUR_LINE_CLEARED;
            default -> 0;
        };
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (BoardInterface.ROW_CHANGE.equals(theEvt.getPropertyName())) {
            display((int) theEvt.getNewValue());
        }
    }
    @Override
    protected void paintComponent(final Graphics theG) {
    }
}
