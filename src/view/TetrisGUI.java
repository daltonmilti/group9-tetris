package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
public final class TetrisGUI implements PropertyChangeListener, PropertyChangeMethods {

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

    /** PropertyChangeSupport for all listeners */
    private final PropertyChangeSupport myPcs;

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
     * Whether or not the board
     */
    private boolean myGameStarted;

    /**
     * Whether or not the game is paused.
     */
    private boolean myGamePause;

    /**
     * The Clip used to play music.
     */
    private Clip myClip;


    private TetrisGUI() {
        super();
        myPcs = new PropertyChangeSupport(this);
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
        this.addPropertyChangeListener((PropertyChangeListener) myGamePanel);
    }

    private void gameStart() {
        TIMER.start();
        BOARD.newGame();
        playMusic("/assets/sound/tetris.wav");
        myGameStarted = true;
    }

    private void pause() {
        myGamePause = !myGamePause;
        if (myGamePause) {
            TIMER.stop();
            myClip.stop();
            myPcs.firePropertyChange(GAME_PAUSED, !myGamePause, myGamePause);
        } else {
            TIMER.start();
            myClip.start();
            myPcs.firePropertyChange(GAME_PAUSED, !myGamePause, myGamePause);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (GAME_STARTING.equals(theEvt.getPropertyName()) && !myGameStarted) {
            gameStart();
        } else if (GAME_END.equals(theEvt.getPropertyName())) {
            myClip.close();
            myGameStarted = false;
        } else if (LEVEL_CHANGING.equals(theEvt.getPropertyName())) {
            TIMER.setDelay((int) (BASE_SPEED / Math.log((int) theEvt.getNewValue() + 1)));
        }
    }

    /**
     * for playing music
     */
    private void playMusic(final String theFilePath) {
        try {
            final URL url = this.getClass().getResource(theFilePath);
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            myClip = AudioSystem.getClip();
            myClip.open(audioIn);
            myClip.start();
            myClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously.
        } catch (final UnsupportedAudioFileException | IOException
                       | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }

    private final class MyKeyAdapter extends KeyAdapter {

        /** Used to store all Keybinds. */
        @SuppressWarnings("All")
        private final HashMap<Integer, Runnable> myKeys = new HashMap<>();


        @SuppressWarnings("checkstyle:ExecutableStatementCount")
        MyKeyAdapter() {
            super();
            myKeys.put(KeyEvent.VK_UP, BOARD::rotateCW);
            myKeys.put(KeyEvent.VK_W, BOARD::rotateCW);
            myKeys.put(KeyEvent.VK_DOWN, this::down);
            myKeys.put(KeyEvent.VK_S, this::down);
            myKeys.put(KeyEvent.VK_LEFT, BOARD::left);
            myKeys.put(KeyEvent.VK_A, BOARD::left);
            myKeys.put(KeyEvent.VK_RIGHT, BOARD::right);
            myKeys.put(KeyEvent.VK_D, BOARD::right);
            myKeys.put(KeyEvent.VK_SPACE, BOARD::drop);
            myKeys.put(KeyEvent.VK_P, TetrisGUI.this::pause);
        }

        private void down() {
            BOARD.down();
            TIMER.restart();
        }

        @Override
        public void keyPressed(final KeyEvent theE) {
            if (TetrisGUI.this.myGameStarted
                && (!TetrisGUI.this.myGamePause || theE.getKeyCode() == KeyEvent.VK_P)
                && myKeys.containsKey(theE.getKeyCode())) {
                myKeys.get(theE.getKeyCode()).run();
            }
        }
    }
}


