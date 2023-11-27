package edu.uw.tcss.app;

import view.TetrisGUI;

/**
 * Class to execute new Board Game
 * and print out board state every update.
 *
 * @author Charles Bryan (cfb3@uw.edu)
 * @author Alan Fowler (acfowler@uw.edu)
 * @version Autumn 2023
 */
public final class SandBox {

    private SandBox() {
        super();
    }

    /**
     * Where execution begins.
     * @param theArgs Used for command line input.
     */
    public static void main(final String[] theArgs) {
        javax.swing.SwingUtilities.invokeLater(TetrisGUI::createAndShowGUI);
    }

}
