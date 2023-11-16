package model;

/**
 * Provides a contract for the Rotation class.
 *
 * @author braggs03
 * @author daltonmilti
 * @author KhobitheCode
 * @author chriseetwo
 * @version Autumn 2023
 */
public interface RotationInterface {

    /**
     * Create a new Rotation from this one rotated clockwise.
     *
     * @return new Rotation object that is rotated 90 degrees clockwise.
     */
    Rotation clockwise();

    /**
     * Create a new Rotation from this one rotated counterclockwise.
     *
     * @return new Rotation object that is rotated 90 degrees clockwise.
     */
    Rotation counterClockwise();
}
