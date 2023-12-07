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
