package it.unibo.crossyroad.model.impl;

import java.util.List;
import java.util.Map;

import it.unibo.crossyroad.model.api.Direction;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.GameManager;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.api.Positionable;

/**
 * Implementation of the GameManager interface.
 * @see GameManager
 */
public class GameManagerImpl implements GameManager{

    private final static Position START_POSITION = new Position(0, 0);
    private final PositionablePlayer player;

    /**
     * Initializes the GameManager with the GameParameters.
     * 
     * @param g the GameParameters to use in the game.
     */
    public GameManagerImpl() {
        this.player = new PositionablePlayer(this.START_POSITION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Positionable> getPositionables() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPositionables'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<EntityType, Long> getActivePowerUps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActivePowerUps'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(Direction d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movePlayer'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    /**
     * Generates a new Chunk.
     */
    private void generateChunk() { }

    /**
     * Wheter the player can move in the given direction.
     * 
     * @param d the direction the player wants to move upon.
     * 
     * @return true if the player can move, false otherwise.
     */
    private boolean canPlayerMove(Direction d) {
        return false;
    }

    /**
     * Checks if the player is colliding with an obstacle.
     */
    private void checkCollisions() { }

    /**
     * Handles the map movement.
     */
    private void moveMap() { }
    
}
