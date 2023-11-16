package model;

/**
 * Provides a contract for the TetrisPiece class.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public interface TeterisPieceInterface {

    /**
     * Return the width of the TetrisPiece.
     *
     * @return the width of the TetrisPiece.
     */
    int getWidth();

    /**
     * Return the height of the TetrisPiece.
     *
     * @return the height of the TetrisPiece.
     */
    int getHeight();

    /**
     * Return the Block type of the TetrisPiece.
     *
     * @return The Block type of the TetrisPiece.
     */
    Block getBlock();

    /**
     * Returns the Points of the TetrisPiece.
     *
     * @return the Points of the TetrisPiece.
     */
    Point[] getPoints();

    /**
     * Returns the Points of the TetrisPiece based on the Rotation.
     *
     * @param theRotation return the points for this Piece based on this rotation
     * @return the Points of the TetrisPiece.
     */
    int[][] getPointsByRotation(Rotation theRotation);
}
