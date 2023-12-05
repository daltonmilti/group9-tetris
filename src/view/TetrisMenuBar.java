package view;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.BoardInterface;

/**
 * TetrisMenuBar Class.
 *
 * @author Christina
 * @version Autumn 2023
 */
public class TetrisMenuBar extends JMenuBar implements TetrisMenuBarInterface {

    /** Used for debugging to ensure no extra panels are instantiated. */
    private static int cnt;

    /** PropertyChangeSupport for all listeners */
    private PropertyChangeSupport myPcs;

    /** Used to construct the JMenuBar for the TetrisGUI. */
    public TetrisMenuBar() {
        super();
        if (cnt > 0) {
            throw new IllegalStateException();
        }
        cnt++;
        createMenuBar();
    }

    private void createMenuBar() {
        final JMenuBar menu = menuBuilder();
        this.add(menu);
        myPcs = new PropertyChangeSupport(this);
    }


    private JMenuBar menuBuilder() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("File");

        final JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(theE -> myPcs.firePropertyChange(
                BoardInterface.GAME_STARTING, null, true));
        menu.add(newGameItem);

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_ESCAPE);
        exitItem.addActionListener(theE -> System.exit(0));
        menu.add(exitItem);

        final JMenu about = new JMenu("About");
        final JMenuItem aboutItem = new JMenuItem("Group Members");
        aboutItem.addActionListener(theE -> JOptionPane.
                showMessageDialog(this, """
                        Group Members:
                        Brandon Ragghianti
                        Christina Situ
                        Dalton Miltimore
                        Khobaib Zafar"""));
        about.add(aboutItem);

        menuBar.add(menu);
        menuBar.add(about);

        return menuBar;
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
