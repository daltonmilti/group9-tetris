package view;

import javax.swing.*;
import java.awt.*;

public final class TetrisGUI extends JPanel {

    private JPanel myLeftPanel;

    private JPanel myRightPanel;


    public TetrisGUI() {
        super();
        buildComponents();
        layoutComponents();
    }

    public static void CreateAndShowGUI() {
        final TetrisGUI mainPanel =
                new TetrisGUI();

        mainPanel.setPreferredSize(new Dimension(800, 1000));

        JFrame window = new JFrame("Group 9 Tetris");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(mainPanel);
        window.pack();
        window.setVisible(true);
    }



    public void buildComponents() {
        myLeftPanel = new LeftPanel();
        myRightPanel = new RightPanel();
    }

    private void layoutComponents() {
        add(myLeftPanel);
        add(myRightPanel);
    }
}

