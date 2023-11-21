package view;

import javax.swing.*;
import java.awt.*;

public class TetrisGUI {

    public static void CreateAndShowGUI() {
        final TetrisGUI mainPanel =
                new TetrisGUI();

        final Dimension frameSize = new Dimension(400, 400);

        final JFrame window = new JFrame("A Message");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(frameSize);

        //window.setContentPane(mainPanel);

        window.pack();

        window.setVisible(true);
    }
}

