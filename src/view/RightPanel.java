package view;

import javax.swing.*;
import java.awt.*;

public final class RightPanel extends JPanel {

    private JPanel myNextPiecePanel;

    private JPanel myCntrlAndScorePanel;


    public RightPanel() {
        setPreferredSize(new Dimension(300, 1000));

        buildComponents();
        layoutComponents();
    }
    public void buildComponents() {
        myNextPiecePanel = new NextPiecePanel();
        myCntrlAndScorePanel = new CntrlAndScorePanel();
    }

    public void layoutComponents() {
        add(myNextPiecePanel);
        add(myCntrlAndScorePanel);
    }
}
