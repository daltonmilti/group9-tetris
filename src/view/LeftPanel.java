package view;

import javax.swing.*;

public class LeftPanel extends JPanel {

    private JPanel myGamePanel;

    public LeftPanel() {
        super();
        buildComponents();
        layoutComponents();
    }

    private void buildComponents() {
        myGamePanel = new GamePanel();
    }

    private void layoutComponents() {
        add(myGamePanel);
    }

}
