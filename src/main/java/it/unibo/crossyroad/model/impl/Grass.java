package it.unibo.crossyroad.model.impl;

import it.unibo.crossyroad.model.api.Dimension;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.Position;

public class Grass extends AbstractChunk {

    public Grass(Position initialPosition, Dimension dimension) {
        super(initialPosition, dimension);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.GRASS;
    }

    @Override
    protected void generateObstacles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateObstacles'");
    }
}
