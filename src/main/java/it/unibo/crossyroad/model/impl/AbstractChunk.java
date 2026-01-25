package it.unibo.crossyroad.model.impl;

import java.util.List;

import it.unibo.crossyroad.model.api.AbstractPositionable;
import it.unibo.crossyroad.model.api.Chunk;
import it.unibo.crossyroad.model.api.Dimension;
import it.unibo.crossyroad.model.api.Obstacle;
import it.unibo.crossyroad.model.api.Position;

public abstract class AbstractChunk extends AbstractPositionable implements Chunk {

    public AbstractChunk(Position initialPosition, Dimension dimension) {
        super(initialPosition, dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Obstacle> getObstacles() { 
        throw new UnsupportedOperationException("Unimplemented method 'getObstacles'");
    }

    /**
     * Generates random Obstacles objects on the Chunk.
     */
    protected abstract void generateObstacles();

    /**
     * Generates random Pickables objects on the Chunk.
     */
    private void generatePickables() { }

    /**
     * Updates the positions of the Obstacles on the Chunk.
     */
    private void moveObstacles() {}

    /**
     * Updates the positions of the Pickables on the Chunk.
     */
    private void movePickables() {}
}
