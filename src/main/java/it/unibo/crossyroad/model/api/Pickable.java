package it.unibo.crossyroad.model.api;

/**
 * A pickable in the game.
 */
public interface Pickable extends Positionable {

    /**
     * If the pickable has not been picked up yet, apply the effect to the game parameters.
     * 
     * @param g the game parameters affected by the pickable's effect
     */
    void pickUp(GameParameters g);

    /**
     * Check if the pickable has already been picked up.
     * 
     * @return true if the pickable is already picked up, false otherwise.
     */
    boolean isPickUp();
}
