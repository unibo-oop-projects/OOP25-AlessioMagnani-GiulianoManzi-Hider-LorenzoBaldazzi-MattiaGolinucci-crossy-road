package it.unibo.crossyroad.model.api;

/**
 * An abstract class representing an active obstacle that has a position in a 2D space.
 */
public abstract class AbstractActiveObstacle implements  ActiveObstacle {
    private Position position;
    private final Dimension dimension;
    private final Direction direction;

    /**
     * It creates a new active obstacle with the given initial position, dimension and direction.
     *
     * @param position the initial position of the active obstacle.
     * @param dimension the dimension of the active obstacle.
     * @param direction the direction of the movement of the active obstacle.
     */
    public AbstractActiveObstacle(final Position position, final Dimension dimension, final Direction direction) {
        if (direction == Direction.UP || direction == Direction.DOWN) {
            throw new IllegalArgumentException("ActiveObstacle can only move LEFT or RIGHT");
        }
        this.position = position;
        this.dimension = dimension;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long deltaTime, double speed) {
        double deltaX = speed * deltaTime / 1000.0 * (this.direction == Direction.LEFT ? -1 : 1);
        this.position = new Position(this.position.x() + deltaX, this.position.y());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return this.dimension;
    }
}
