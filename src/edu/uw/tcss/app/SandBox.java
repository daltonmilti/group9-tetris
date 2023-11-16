package edu.uw.tcss.app;

import java.util.logging.Logger;
import model.Board;

/**
 * Class to execute new Board Game
 * and print out board state every update.
 *
 * @author Charles Bryan (cfb3@uw.edu)
 * @author Alan Fowler (acfowler@uw.edu)
 * @version Autumn 2023
 */
public final class SandBox {

    /**
     * Initilization of Logger object used for output.
     */
    private static final Logger LOGGER = Logger.getLogger(SandBox.class.getName());


    private SandBox() {
        super();
    }

    /**
     * Where execution begins.
     * @param theArgs Used for command line input.
     */
    public static void main(final String[] theArgs) {

        final Board b = new Board();

        b.newGame();
        LOGGER.info(b.toString());
        b.step();
        b.step();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.step();
        LOGGER.info(b.toString());
        b.drop();
        LOGGER.info(b.toString());

    }

}
