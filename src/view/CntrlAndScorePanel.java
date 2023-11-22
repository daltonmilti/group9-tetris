package view;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class CntrlAndScorePanel extends JPanel {
    private JPanel myControlsPanel;

    private JPanel myScorePanel;

    public CntrlAndScorePanel() {
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(300, 650));

        buildComponents();
        layoutComponents();
    }

    private void buildComponents() {
        myControlsPanel = new ControlsPanel();
        myScorePanel = new ScorePanel();
    }

    private void layoutComponents() {
//        add(myControlsPanel);
//        add(myScorePanel);
    }
}
