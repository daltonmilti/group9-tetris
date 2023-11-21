package edu.uw.tcss.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AppDemo {
    public static void main(String[] args) {



        SwingUtilities.invokeLater(() -> {

            GridBagLayout example = new GridBagLayout();
            JPanel scorePanel = new JPanel();
            scorePanel.setLayout(example);
            scorePanel.setPreferredSize(new Dimension(300, 400));

            GridBagConstraints c = new GridBagConstraints();

            JButton northButton = new JButton("North");
            northButton.setBackground(Color.BLUE);
            northButton.setMinimumSize(new Dimension(200, 200));
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 5;
            c.weighty = 5;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.ipadx = 100;
            c.ipady = 100;
            scorePanel.add(northButton, c);

            JButton southButton = new JButton("South");
            southButton.setBackground(Color.GREEN);
            southButton.setMinimumSize(new Dimension(200, 200));
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 5;
            c.weightx = 5;
            c.weighty = 5;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.ipadx = 200;
            c.ipady = 200;
            scorePanel.add(southButton, c);

            JFrame frame = new JFrame();


            JButton gameButton = new JButton("Game");
            gameButton.setBackground(Color.RED);



            JPanel gameWindow = new JPanel();
            gameWindow.add(gameButton);
            gameButton.setPreferredSize(new Dimension(300, 600));

            JPanel content = new JPanel();
            content.setLayout(new BorderLayout());
            content.add(scorePanel, BorderLayout.CENTER);
            content.add(gameButton, BorderLayout.WEST);


            frame.setContentPane(content);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setPreferredSize(new Dimension(600, 600));
            frame.pack();
            frame.setVisible(true);
        });

    }
}