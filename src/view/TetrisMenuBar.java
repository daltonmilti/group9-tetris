package view;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * TetrisMenuBar Class.
 *
 * @author Christina
 * @version Autumn 2023
 */
public class TetrisMenuBar extends JMenuBar {

    /**
     * Constructor.
     */
    public TetrisMenuBar() {
        super();
        createMenuBar();
    }

    private void createMenuBar() {
        final JMenu menu = menuBuilder();
        this.add(menu);
    }


    private JMenu menuBuilder() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("File");

        final JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(theE -> JOptionPane.
                showMessageDialog(this, "a new game is starting"));
        menu.add(newGameItem);

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_ESCAPE);
        exitItem.addActionListener(theE -> System.exit(0));
        menu.add(exitItem);

        final JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(theE -> JOptionPane.
                showMessageDialog(this, """
                        Group Members:
                        Brandon Ragghianti
                        Christina Situ
                        Dalton Miltimore
                        Khobaib Zafar"""));
        menu.add(aboutItem);

        return menu;
    }



}
