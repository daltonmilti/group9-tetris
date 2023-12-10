package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    /**
     * Times New Roman font to use.
     */
    private static final String TIMES_NEW_ROMAN_FONT = "Times New Roman";

    /**
     * Text constant for the score.
     */
    private static final String SCORE_LABEL = "Current Score: ";

    /**
     * Text constant for the level.
     */
    private static final String LEVEL_LABEL = "Current Level: ";

    /**
     * Text constant for the rows cleared.
     */
    private static final String ROWS_LABEL = "Rows Cleared: ";

    /**
     * Text constant for the rows cleared.
     */
    private static final String ROWS_TILL_LABEL = "Rows Till Next Level: ";

    /**
     * Font size for labels.
     */
    private static final int FONT_SIZE = 30;

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

    /** The amount of cleared rows in the current game. */
    private int myClearedRows;

    /** The current level the user is on */
    private int myLevel;

    /**
     * The current or previous level.
     */
    private int myPastLevel;

    /** The users current score */
    private int myScore;

    /**
     * Label for the score.
     */
    private JLabel myScoreText;

    /**
     * Label for the level.
     */
    private JLabel myLevelText;

    /**
     * Label for the rows cleared.
     */
    private JLabel myRowText;

    /**
     * Label for the rows needed to progress to next level.
     */
    private JLabel myRowTillNext;

    /** PropertyChangeSupport for all listeners */
    private final PropertyChangeSupport myPcs;

    /**
     * Creates the info panel for the Tetris GUI.
     */
    public InfoPanel() {
        super();
        myPcs = new PropertyChangeSupport(this);
        buildComponents();
    }

    //Method adds many JLabels to a specific region.
    //Not really possible to split up into different methods or simplify.
    @SuppressWarnings("OverlyLongMethod")
    private void buildComponents() {
        myLevel = 1;
        myPastLevel = 1;
        this.setPreferredSize(
                new Dimension(TetrisGUI.SIZE / 2, TetrisGUI.SIZE / 2));
        this.setBackground(Color.WHITE);
        myScoreText = new JLabel();
        myScoreText.setFont(new Font(TIMES_NEW_ROMAN_FONT, Font.BOLD, FONT_SIZE));
        myScoreText.setText(SCORE_LABEL + myScore);
        this.add(myScoreText);
        myLevelText = new JLabel();
        myLevelText.setFont(new Font(TIMES_NEW_ROMAN_FONT, Font.BOLD, FONT_SIZE));
        myLevelText.setText(LEVEL_LABEL + myLevel);
        this.add(myLevelText);
        myRowText = new JLabel();
        myRowText.setFont(new Font(TIMES_NEW_ROMAN_FONT, Font.BOLD, FONT_SIZE));
        myRowText.setText(ROWS_LABEL + myClearedRows);
        this.add(myRowText);
        myRowTillNext = new JLabel();
        myRowTillNext.setFont(new Font(TIMES_NEW_ROMAN_FONT, Font.BOLD, FONT_SIZE));
        myRowTillNext.setText(ROWS_TILL_LABEL + 0);
        this.add(myRowTillNext);
        this.add(createControlBox());
    }

    //Method adds many JLabels to a specific Box.
    //Not really possible to split up into different methods or simplify.
    @SuppressWarnings({"toocomplex", "OverlyLongMethod"})
    private Box createControlBox() {
        final Box b = Box.createVerticalBox();
        final Font font = new Font(TIMES_NEW_ROMAN_FONT, Font.PLAIN, 20);
        b.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        JLabel currentLabel = new JLabel(" Rotate Clockwise:   Up Arrow or W   ");
        currentLabel.setFont(font);
        b.add(currentLabel);
        currentLabel = new JLabel(" Move Down:           Down Arrow or S  ");
        currentLabel.setFont(font);
        b.add(currentLabel);
        currentLabel = new JLabel(" Move Left:              Left Arrow or D");
        currentLabel.setFont(font);
        b.add(currentLabel);
        currentLabel = new JLabel(" Move Right:            Right Arrow or A");
        currentLabel.setFont(font);
        b.add(currentLabel);
        currentLabel = new JLabel(" Drop:                      Spacebar ");
        currentLabel.setFont(font);
        b.add(currentLabel);
        currentLabel = new JLabel(" Pause:                     P ");
        currentLabel.setFont(font);
        b.add(currentLabel);
        return b;
    }

    private void display(final int theRowsCleared) {
        myClearedRows += theRowsCleared;
        myLevel = (myClearedRows / ROWS_TO_PROGRESS) + 1;
        if (myLevel != myPastLevel) {
            myPastLevel = myLevel;
            myPcs.firePropertyChange(PropertyChangeMethods.LEVEL_CHANGING, null, myLevel);
        }
        myScore += calculateScore(theRowsCleared);
        myScoreText.setText(SCORE_LABEL + myScore);
        myLevelText.setText(LEVEL_LABEL + myLevel);
        myRowText.setText(ROWS_LABEL + myClearedRows);
        myRowTillNext.setText(ROWS_TILL_LABEL
                             + ((ROWS_TO_PROGRESS * myLevel) - myClearedRows));
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

    private void resetInfo() {
        myScore = 0;
        myLevel = 1;
        myPastLevel = 1;
        myClearedRows = 0;
        display(0);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (PropertyChangeMethods.ROW_CHANGE.equals(theEvt.getPropertyName())) {
            display((int) theEvt.getNewValue());
        } else if (PropertyChangeMethods.INFO_RESET.equals(theEvt.getPropertyName())) {
            resetInfo();
        }
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }
}
