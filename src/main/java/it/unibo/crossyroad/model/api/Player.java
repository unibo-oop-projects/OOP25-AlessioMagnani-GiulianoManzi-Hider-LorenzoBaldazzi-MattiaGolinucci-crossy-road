package it.unibo.crossyroad.model.api;

/**
 * An interface representing the player (chicken) entity in the game.
 */
public interface Player extends Positionable {
    /**
     * Moves the player in the given direction.
     *
     * @param direction the direction to move the player
     */
    void move(Direction direction);
}
