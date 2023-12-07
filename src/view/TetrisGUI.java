package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.BoardInterface;

/**
 * Creates a Tetris GUI.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class TetrisGUI implements PropertyChangeListener {

    /**
     * The main board used for the Tetris project.
     */
    public static final BoardInterface BOARD = new Board();

    /** Constant used to size screen. */
    public static final int SIZE = 800;

    /**
     * Base delay on timer.
     */
    private static final int BASE_SPEED = 1000;

    /** Used to update step() the board after some time. */
    private static final Timer TIMER = new Timer(BASE_SPEED, theE -> BOARD.step());

    /** The Tetris Frame. */
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

    /**
     * The Game over panel.
     */
    private JPanel myGameOverPanel;

    /**
     * Whether or not the board
     */
    private boolean myGameStarted;

    /**
     * Clip for playing music.
     */
    private Clip myClip;

    private TetrisGUI() {
        super();
        myGameStarted = false;
        buildComponents();
        layoutComponents();
        addListenersAndPropertyChangeListeners();
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

        //GameOverPanel
        myGameOverPanel = new GameOverPanel();

    }
    private void layoutComponents() {

        //Right Panel
        myRightPanel.setPreferredSize(
                new Dimension(SIZE / 2, SIZE));
        myRightPanel.setLayout(new GridLayout(0, 1, 0, 0));
        myRightPanel.add(myNextPiecePanel);
        myRightPanel.add(myInfoPanel);

        //Main Panel
        myMainPanel.setPreferredSize(new Dimension(SIZE, SIZE));
        myMainPanel.setLayout(new GridLayout(1, 0, 0, 0));
        myMainPanel.add(myGamePanel);
        myMainPanel.add(myRightPanel);

        //Game Panel
        myGamePanel.add(myGameOverPanel);

        //Window
        myWindow.setLayout(new GridLayout(1, 0, 0, 0));
        myWindow.setJMenuBar(myMenuBar);
        myWindow.add(myMainPanel);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setContentPane(myMainPanel);
        myWindow.setResizable(false);
        myWindow.pack();
        myWindow.setVisible(true);
        myWindow.requestFocus();

    }

    private void addListenersAndPropertyChangeListeners() {
        myWindow.addKeyListener(new MyKeyAdapter());
        myMenuBar.addPropertyChangeListener(this);
        BOARD.addPropertyChangeListener(this);
        BOARD.addPropertyChangeListener((PropertyChangeListener) myGamePanel);
        BOARD.addPropertyChangeListener((PropertyChangeListener) myNextPiecePanel);
        BOARD.addPropertyChangeListener((PropertyChangeListener) myInfoPanel);
        myInfoPanel.addPropertyChangeListener(this);
    }

    private void gameStart() {
        TIMER.start();
        BOARD.newGame();
        playMusic("/tetris.wav");
        myGameStarted = true;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (BoardInterface.GAME_STARTING.equals(theEvt.getPropertyName())) {
            gameStart();
            removeGameOverPanel();
        } else if (BoardInterface.GAME_END.equals(theEvt.getPropertyName())) {
            myGameStarted = false;
            stopMusic();
            showGameOverPanel();
        } else if (BoardInterface.LEVEL_CHANGING.equals(theEvt.getPropertyName())) {
            TIMER.setDelay((int) (BASE_SPEED / Math.log((int) theEvt.getNewValue() + 1)));
        }
    }

    /**
     * for playing music
     * @param theFilePath path to tetris.wav in assets.sound
     */
    private void playMusic(final String theFilePath) {
        try {
            final URL url = Objects.requireNonNull(this.getClass().getResource(theFilePath));
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            myClip = AudioSystem.getClip(); // Initialize the class member variable
            myClip.open(audioIn);
            myClip.start();
            myClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously.
        } catch (final UnsupportedAudioFileException | IOException
                       | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void stopMusic() {
        if (myClip != null) {
            myClip.stop(); // Stop the music
        }
    }

    private void showGameOverPanel() {
        myGameOverPanel.setVisible(true);
    }
    private void removeGameOverPanel() {
        myGameOverPanel.setVisible(false);
    }

    private final class MyKeyAdapter extends KeyAdapter {

        /** Used to store all Keybinds. */
        @SuppressWarnings("All")
        private final HashMap<Integer, Runnable> myKeys = new HashMap<>();

        MyKeyAdapter() {
            super();
            myKeys.put(KeyEvent.VK_UP, BOARD::rotateCW);
            myKeys.put(KeyEvent.VK_W, BOARD::rotateCW);
            myKeys.put(KeyEvent.VK_DOWN, BOARD::down);
            myKeys.put(KeyEvent.VK_S, BOARD::down);
            myKeys.put(KeyEvent.VK_LEFT, BOARD::left);
            myKeys.put(KeyEvent.VK_A, BOARD::left);
            myKeys.put(KeyEvent.VK_RIGHT, BOARD::right);
            myKeys.put(KeyEvent.VK_D, BOARD::right);
            myKeys.put(KeyEvent.VK_SPACE, BOARD::drop);
        }

        @Override
        public void keyPressed(final KeyEvent theE) {
            if (TetrisGUI.this.myGameStarted && myKeys.containsKey(theE.getKeyCode())) {
                myKeys.get(theE.getKeyCode()).run();
            }
        }
    }
}


