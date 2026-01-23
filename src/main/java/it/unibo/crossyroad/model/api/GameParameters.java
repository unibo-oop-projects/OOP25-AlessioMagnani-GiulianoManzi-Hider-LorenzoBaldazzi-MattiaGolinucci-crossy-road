package it.unibo.crossyroad.model.api;

import it.unibo.crossyroad.model.impl.GameParametersImpl;

/**
 * Interface representing game parameters and settings.
 */
public interface GameParameters {

    /**
     * Sets the new coin multiplier.
     *
     * @param coinMultiplier the new coin multiplier.
     */
    void setCoinMultiplier(int coinMultiplier);

    /**
     * Sets the new car speed multiplier.
     *
     * @param carSpeedMultiplier the new car speed multiplier.
     */
    void setCarSpeedMultiplier(double carSpeedMultiplier);

    /**
     * Sets the new train speed multiplier.
     *
     * @param trainSpeedMultiplier the new train speed multiplier.
     */
    void setTrainSpeedMultiplier(double trainSpeedMultiplier);

    /**
     * Sets the invincibility status.
     *
     * @param invincibility true if invincible, false otherwise.
     */
    void setInvincibility(boolean invincibility);

    /**
     * Gets the current coin multiplier.
     *
     * @return the coin multiplier.
     */
    int getCoinMultiplier();

    /**
     * Gets the current car speed multiplier.
     *
     * @return the car speed multiplier.
     */
    double getCarSpeedMultiplier();

    /**
     * Gets the current train speed multiplier.
     *
     * @return the train speed multiplier.
     */
    double getTrainSpeedMultiplier();

    /**
     * Checks if the player is invincible.
     *
     * @return true if invincible, false otherwise.
     */
    boolean isInvincible();

    /**
     * Gets the current coin count.
     *
     * @return the coin count.
     */
    int getCoinCount();

    /**
     * Increments the coin based on the coin multiplier.
     */
    void incrementCoinCount();

    /**
     * Loads game parameters from a file.
     *
     * @param filepath the path to the file.
     * @return a GameParametersImpl instance with loaded parameters.
     */
    GameParametersImpl loadFromFile(String filepath);
}
