package view;

import javax.swing.*;
import java.awt.*;

public class TetrisGUI extends JPanel {

    public TetrisGUI() {
        super();
        buildComponents();
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

    public static void buildComponents() {
        GamePanel gamePanel = new GamePanel();
        NextPiecePanel nextPiecePanel = new NextPiecePanel();
        InfoPanel infoPanel = new InfoPanel();
    }
}

