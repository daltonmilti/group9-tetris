package view;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

/**
 * TetrisMenuBar Class.
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public class TetrisMenuBar extends JMenuBar implements PropertyChangeMethods {

    /**
     * Tick Spacing for Slider
     */
    private static final int TICK_SPACING = 50;

    /** PropertyChangeSupport for all listeners */
    private PropertyChangeSupport myPcs;

    /** Used to construct the JMenuBar for the TetrisGUI. */
    public TetrisMenuBar() {
        super();
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
        newGameItem.addActionListener(theEvent -> myPcs.firePropertyChange(
                GAME_STARTING, null, true));
        menu.add(newGameItem);

        final JMenuItem endItem = new JMenuItem("End Game");
        endItem.addActionListener(theE -> myPcs.firePropertyChange(GAME_END, null, true));
        menu.add(endItem);

        final JMenu about = getjMenu();

        final JMenu options = getGameOptions();

        menuBar.add(options);
        menuBar.add(menu);
        menuBar.add(about);

        return menuBar;
    }

    private JMenu getjMenu() {
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
        final JMenuItem scoringItem = getjMenuItem();
        about.add(scoringItem);
        return about;
    }

    private JMenu getGameOptions() {
        final JMenu options = new JMenu("Options");

        final JCheckBoxMenuItem hardMode = new JCheckBoxMenuItem("Hard Mode");
        hardMode.addActionListener(theE ->
                myPcs.firePropertyChange(HARD_MODE, null, hardMode.isSelected()));
        options.add(hardMode);

        final JLabel sliderName = new JLabel("Game Speed");
        options.add(sliderName);

        final JSlider slider = new JSlider(50, 200, 50);
        slider.addChangeListener(theE ->
                myPcs.firePropertyChange(SPEED_CHANGING, null, slider.getValue()));
        slider.setMajorTickSpacing(TICK_SPACING);
        slider.setSnapToTicks(true);


        options.add(slider);
        return options;
    }

    private JMenuItem getjMenuItem() {
        final JMenuItem scoringItem = new JMenuItem("Scoring Rules");
        scoringItem.addActionListener(theE -> JOptionPane.
                showMessageDialog(this, """
                        Score is calculated as follows:
                        When line(s) are cleared, the score gained is the current
                        level, n, times a multiplyer based on the lines cleared.
                        
                        1 line      2 line      3 line       4 line
                        n * 40    n * 100    n * 300    n * 1200
                        """));
        return scoringItem;
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
