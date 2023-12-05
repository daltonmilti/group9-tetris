package view;

import model.BoardInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.BoardInterface;



public class GameOverPanel extends JPanel implements ActionListener {

    private final Dimension mySize = new Dimension(200, 200);
    private JButton myNewGameButt;
    private JButton myTitleScreenButt;
    private JButton myExitButt;
    private JButton myAreYouSure1;
    private JButton myAreYouSure2;
    private JButton myAreYouSure3;
    private PropertyChangeSupport myPcs;


    public GameOverPanel() {
        setVisible(false);
        setSize(mySize);
        setLocation(0, 300);

        buildComponents();
        layoutComponents();
        myPcs = new PropertyChangeSupport(this);

        revalidate();
    }

    private void layoutComponents() {
        add(myNewGameButt);
        add(myTitleScreenButt);
        add(myExitButt);
    }

    private void buildComponents() {
        myNewGameButt = new JButton("New Game");
        myTitleScreenButt = new JButton("Back to Title Screen");
        myExitButt = new JButton("Exit Game");
        myAreYouSure1 = new JButton("Are you sure you want to leave?");
        myAreYouSure2 = new JButton("Are you SURE you're sure?");
        myAreYouSure3 = new JButton("Our game is REALLLLY awesome tho :(");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        myNewGameButt.addActionListener(theE -> myPcs.firePropertyChange(
                BoardInterface.GAME_STARTING, null, true));
    }

    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }
}
