package view;

import model.BoardInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.BoardInterface;



public class GameOverPanel extends JPanel {

    private final Dimension mySize = new Dimension(300, 200);


    public GameOverPanel() {
        setVisible(false);
        setSize(mySize);
        setLocation(150, 500);

        buildComponents();
        layoutComponents();
        revalidate();
    }

    private void layoutComponents() {

    }

    private void buildComponents() {

    }

}
