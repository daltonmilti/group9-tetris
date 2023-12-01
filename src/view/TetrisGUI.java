package view;

import model.Board;
import model.BoardInterface;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * Creates a Tetris GUI.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class TetrisGUI extends JPanel {

    /**
     * The main board used for the Tetris project.
     */
    public static final BoardInterface THE_BOARD = new Board();

    /**
     * Constanst used to size screen.
     */
    public static final int SIZE = 800;

    /**
     * Used for debugging, if value is ever greater than one, an exception is thrown.
     */
    private static int cnt;

    /**
     * The Tetris Frame.
     */
    private JFrame myWindow;

    /**
     * The Tetris Menu Bar.
     */
    private JMenuBar myMenuBar;

    /**
     * The Tetris Main Panel.
     */
    private JPanel myMainPanel;

    /**
     * The Tetris Right Panel. Used to organize myNextPiecePanel and myInfoPanel.
     */
    private JPanel myRightPanel;

    /**
     * The Tetris Game Panel.
     */
    private JPanel myGamePanel;

    /**
     * The Tetris Next Piece Panel.
     */
    private JPanel myNextPiecePanel;

    /**
     * The Tetris Info Panel.
     */
    private JPanel myInfoPanel;

    private TetrisGUI() {
        super();
        if (cnt > 0) {
            throw new IllegalStateException();
        }
        buildComponents();
        layoutComponents();
    }

    /**
     * Creates a Tetris GUI.
     */
    public static void createAndShowGUI() {
        final TetrisGUI l = new TetrisGUI();
    }
    private void buildComponents() {
        //Frame
        myWindow = new JFrame("Tetris - Group 9");

        //Menu Bar
        myMenuBar = new TetrisMenuBar();

        //MainPanel
        myMainPanel = new JPanel();

        //RightPanel
        myRightPanel = new JPanel();

        //GamePanel
        myGamePanel = new GamePanel();

        //NextPanel
        myNextPiecePanel = new NextPiecePanel();

        //InfoPanel
        myInfoPanel = new InfoPanel();

    }
    private void layoutComponents() {

        //Right Panel
        myRightPanel.setPreferredSize(
                new Dimension(SIZE / 2, SIZE));
        myRightPanel.setLayout(new GridLayout(0, 1, 0, 0));
        myRightPanel.add(myNextPiecePanel);
        myRightPanel.add(myInfoPanel);

        //Main Panel
        myMainPanel.setSize(new Dimension(SIZE, SIZE));
        myMainPanel.setLayout(new GridLayout(1, 0, 0, 0));
        myMainPanel.add(myGamePanel);
        myMainPanel.add(myRightPanel);

        //Window
        myWindow.setJMenuBar(myMenuBar);
        myWindow.add(myMainPanel);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setContentPane(myMainPanel);
        myWindow.setResizable(true);
        myWindow.pack();
        myWindow.setVisible(true);
    }

    private static final class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyReleased(final KeyEvent theE) {
            super.keyReleased(theE);
            System.out.println("Key " + theE.getKeyChar());
            System.out.println("Code " + theE.getKeyCode());
        }
    }
}


