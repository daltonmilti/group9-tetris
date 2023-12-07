package model;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Provides a contract for the Board class.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public interface BoardInterface {

    /** Used to notify listeners the game is ending. */
    String GAME_END = "The Game is Ending";

    /** Used to notify listeners the game is ending. */
    String CURRENT_PIECE_CHANGING = "Current Piece is Changing";

    /** Used to notify listeners the frozen blocks changing. */
    String ROW_CHANGE = "My Rows Have Changed";

    /** Used to notify listeners the next piece changing. */
    String NEXT_PIECE_CHANGE = "Next Piece is Changing";

    /** Used to notify listeners the game is starting. */
    String GAME_STARTING = "New Game is Starting";

    /** Used to notify listeners the game has a new piece added to FrozenPieces. */
    String FROZEN_CHANGING = "Frozen Blocks Have Changed";

    /** Used to notify listeners the level is changing. */
    String LEVEL_CHANGING = "Level is changing";

    /** Used to notify listeners the game is paused. */
    String GAME_PAUSED = "The game is paused";

    /**
     * Get the width of the board.
     *
     * @return Width of the board.
     */
    int getWidth();

    /**
     * Get the height of the board.
     *
     * @return Height of the board.
     */
    int getHeight();

    /**
     * Resets the board for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    void newGame();

    /**
     * Sets a non-random sequence of pieces to loop through.
     *
     * @param thePieces the List of non-random TetrisPieces.
     */
    void setPieceSequence(List<TetrisPiece> thePieces);

    /**
     * Advances the board by one 'step'.
     * <p>
     * This could include
     * - moving the current piece down 1 line
     * - freezing the current piece if appropriate
     * - clearing full lines as needed
     */
    void step();

    /**
     * Try to move the movable piece down.
     * Freeze the Piece in position if down tries to move into an illegal state.
     * Clear full lines.
     */
    void down();

    /**
     * Try to move the movable piece left.
     */
    void left();

    /**
     * Try to move the movable piece right.
     */
    void right();

    /**
     * Try to rotate the movable piece in the clockwise direction.
     */
    void rotateCW();

    /**
     * Try to rotate the movable piece in the counter-clockwise direction.
     */
    void rotateCCW();

    /**
     * Drop the piece until piece is set.
     */
    void drop();

    /**
     * Used for adding a PropertyChangeListener. This listener is registered to all properties.
     * @param theListener is the PropertyChangeListener to be added.
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Used for adding a PropertyChangeListener to a specific String.
     * @param thePropertyName the String we want to give the to the Listener.
     * @param theListener is the PropertyChangeListener to be added.
     */
    void addPropertyChangeListener(String thePropertyName,
                                   PropertyChangeListener theListener);

    /**
     * Used for removing a PropertyChangeListener.
     * @param theListener is the PropertyChangeListener to be removed.
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Used for removing a PropertyChangeListener.
     * @param thePropertyName the String linked to the Listener.
     * @param theListener is the PropertyChangeListener to be added.
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);
}
