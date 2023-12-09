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
import java.util.Random;
import javax.sound.sampled.*;
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
public final class TetrisGUI implements PropertyChangeListener, PropertyChangeMethods{

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
            pauseMusic();
            myPcs.firePropertyChange(BoardInterface.GAME_PAUSED, !myGamePause, myGamePause);
        } else {
            TIMER.start();
            pauseMusic();
            myPcs.firePropertyChange(BoardInterface.GAME_PAUSED, !myGamePause, myGamePause);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if (BoardInterface.GAME_STARTING.equals(theEvt.getPropertyName()) && !myGameStarted) {
            gameStart();
        } else if (BoardInterface.GAME_END.equals(theEvt.getPropertyName())
                   && (boolean) theEvt.getNewValue()) {
            myClip.close();
            myGameStarted = false;
            playGameOver();
        } else if (BoardInterface.LEVEL_CHANGING.equals(theEvt.getPropertyName())) {
            TIMER.setDelay((int) (BASE_SPEED / Math.log((int) theEvt.getNewValue() + 1)));
            playLevelUp();
        }
    }

    /**
     * game end sounds
     */
    private void playGameOver() {
        String filePath = "/assets/sound/charles_bad_1.wav";
        Clip clip;

        try {
            final URL url = this.getClass().getResource(filePath);
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (final UnsupportedAudioFileException | IOException
                       | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * level up sound method
     */
    private void playLevelUp() {
        final Random r = new Random();
        String filePath = "";
        final int randomInt = r.nextInt(3);
        final Clip clip;
        if (randomInt == 0) {
            filePath = "/assets/sound/charles_yes_1.wav";
        } else if (randomInt == 1) {
            filePath = "/assets/sound/charles_yes_2.wav";
        } else {
            filePath = "/assets/sound/charles_yes_3.wav";
        }

        try {
            final URL url = this.getClass().getResource(filePath);
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (final UnsupportedAudioFileException | IOException
                       | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * for playing music
     */
    private void playMusic(final String theFilePath) {
        final float volume = -15.00f;
        try {
            final URL url = this.getClass().getResource(theFilePath);
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            myClip = AudioSystem.getClip();
            myClip.open(audioIn);
            final FloatControl gainControl =
                    (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            myClip.start();
            myClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously.
        } catch (final UnsupportedAudioFileException | IOException
                       | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * pause music
     */
    private void pauseMusic() {
        final FloatControl gainControl =
                (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
        final float volume;
        if (myGamePause) {
            volume = -30.0f;
        } else {
            volume = -15.0f;
        }
        gainControl.setValue(volume);
    }

    private void drop() {
        BOARD.drop();
        final float volume = -30.00f;
        Clip clip;
        try {
            final URL url = this.getClass().getResource("/assets/sound/explosion.wav");
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            final FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
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
            myKeys.put(KeyEvent.VK_DOWN, BOARD::down);
            myKeys.put(KeyEvent.VK_S, BOARD::down);
            myKeys.put(KeyEvent.VK_LEFT, BOARD::left);
            myKeys.put(KeyEvent.VK_A, BOARD::left);
            myKeys.put(KeyEvent.VK_RIGHT, BOARD::right);
            myKeys.put(KeyEvent.VK_D, BOARD::right);
            myKeys.put(KeyEvent.VK_SPACE, TetrisGUI.this::drop);
            myKeys.put(KeyEvent.VK_P, TetrisGUI.this::pause);
        }

        @Override
        public void keyPressed(final KeyEvent theE) {
            if (TetrisGUI.this.myGameStarted && (!TetrisGUI.this.myGamePause || theE.getKeyCode() == KeyEvent.VK_P) && myKeys.containsKey(theE.getKeyCode())) {
                myKeys.get(theE.getKeyCode()).run();
            }
        }
    }
}


