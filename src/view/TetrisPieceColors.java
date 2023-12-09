package view;

import java.awt.Color;
import model.Block;
import model.MovableTetrisPiece;
import model.TetrisPiece;

/**
 * Simple utility class to find the color of a TetrisPiece or MovableTetrisPiece.
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public final class TetrisPieceColors {

    private TetrisPieceColors() {
        super();
    }

    /**
     * Used to find color of the Block.
     * @param theBlock The Block.
     * @return the Color of the Block.
     */
    public static Color getColor(final Block theBlock) {
        return switch (theBlock) {
            case EMPTY -> Color.BLACK;
            case I -> Color.CYAN;
            case J -> Color.BLUE;
            case L -> Color.ORANGE;
            case O -> Color.YELLOW;
            case S -> Color.GREEN;
            case T -> Color.MAGENTA;
            case Z -> Color.RED;
        };
    }

    /**
     * Used to find color of MoveableTetrisPiece.
     * @param thePiece The MoveableTetrisPiece.
     * @return The color of the MoveableTetrisPiece.
     */
    public static Color getColor(final MovableTetrisPiece thePiece) {
        return getColor(thePiece.getTetrisPiece());
    }

    /**
     * Used to find the color of TetrisPiece.
     * @param thePiece The TetrisPiece.
     * @return The color of the TetrisPiece.
     */
    public static Color getColor(final TetrisPiece thePiece) {
        return getColor(thePiece.getBlock());
    }

    /**
     * Grabs a random Tetris color.
     * @return A color of random value.
     */
    public static Color random() {
        return getColor(TetrisPiece.getRandomPiece());
    }
}
