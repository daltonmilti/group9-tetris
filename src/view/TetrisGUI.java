package view;

import javax.swing.*;
import java.awt.*;

public class TetrisGUI extends JPanel {

    private JPanel myGamePanel;

    private JPanel myNextPiecePanel;

    private JPanel myInfoPanel;


    public TetrisGUI() {
        super();
        buildComponents();
        layoutComponents();
    }

    public static void CreateAndShowGUI() {
        final TetrisGUI mainPanel =
                new TetrisGUI();

        final Dimension frameSize = new Dimension(400, 400);
        final JFrame window = new JFrame("A Message");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(frameSize);
        window.setContentPane(mainPanel);
        window.pack();
        window.setVisible(true);
    }

    public void buildComponents() {
        myGamePanel = new GamePanel();
        myNextPiecePanel = new JPanel();
        myInfoPanel = new JPanel();

    }

    private void layoutComponents() {

        final JPanel mainPanel = new JPanel();
        mainPanel.add(myGamePanel);
        mainPanel.add(myNextPiecePanel);
        mainPanel.add(myInfoPanel);
    }
}

