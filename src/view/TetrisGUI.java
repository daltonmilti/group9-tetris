package view;

import javax.swing.*;
import java.awt.*;

public class TetrisGUI extends JPanel {

    private JPanel myGamePanel;

    private JPanel myNextPiecePanel;

    private JPanel myInfoPanel;

    private static JFrame window = new JFrame("Tetris");


    public TetrisGUI() {
        super();
        buildComponents();
        layoutComponents();
    }

    public static void CreateAndShowGUI() {
        final TetrisGUI mainPanel = new TetrisGUI();

//        final Dimension frameSize = new Dimension(400, 400);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setSize(frameSize);
        window.setPreferredSize(new Dimension(300, 400));
        final TetrisMenuBar menuBar = new TetrisMenuBar();
        window.setJMenuBar(menuBar);
        window.setContentPane(mainPanel);
        window.pack();
        window.setVisible(true);
    }

    public JFrame getWindow() {
        return window;
    }



    public void buildComponents() {
        myGamePanel = new GamePanel(); // i dunno
        myNextPiecePanel = new JPanel();
        myInfoPanel = new JPanel();

    }

    private void layoutComponents() {

        final TetrisMenuBar menuBar = new TetrisMenuBar();
        window.add(menuBar);
        final JPanel mainPanel = new JPanel();
        mainPanel.add(myGamePanel);
        mainPanel.add(myNextPiecePanel);
        mainPanel.add(myInfoPanel);
    }
}

