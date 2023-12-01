package model;

import java.beans.PropertyChangeListener;

/**
 * Used to properly implement PropertyChangeListners and keep track
 * of our constants used for these constant change listners.
 * @author braggs03
 * @version 1
 */
public interface PropertyChangeBoard {

    /** Used to noitify listeners the game is ending. */
    String GAME_END = "The Game is Ending";

    /** Used to noitify listeners the game is ending. */
    String CURRENT_PIECE_CHANGING = "Current Piece is Changing";

    /** Used to noitify listeners the frozen blocks changing. */
    String ROW_CHANGE = "My Rows Have Changed";

    /** Used to noitify listeners the next piece changing. */
    String NEXT_PIECE_CHANGE = "Next Piece is Changing";

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
