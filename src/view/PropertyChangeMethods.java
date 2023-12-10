package view;

import java.beans.PropertyChangeListener;

/**
 * Used to exposed PropertyChangeListener methods to the TetrisMenuBar class.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public interface PropertyChangeMethods {

    /** Used to notify listeners the game is ending. */
    String GAME_END = "The Game is Ending";

    /** Used to notify listeners the game is ending. */
    String CURRENT_PIECE_CHANGING = "Current Piece is Changing";

    /** Used to notify listeners the game is ending. */
    String GHOST_PIECE_CHANGING = "Ghost Piece is Changing";

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

    /** Used to notify listeners that Hard Mode is changing. */
    String HARD_MODE = "Hard mode is chaning";

    /** Used to notify listeners that Game Speed is changing. */
    String SPEED_CHANGING = "Game speed is changing";

    /** Used to notify listeners that Game Speed is changing. */
    String INFO_RESET = "Reset Info";


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
