package it.unibo.crossyroad.model.api;

/**
 * Enum representing a direction.
 */
public enum Direction {
    UP(Position.of(-1, 0)),
    DOWN(Position.of(1, 0)),
    LEFT(Position.of(0, -1)),
    RIGHT(Position.of(0, 1));

    private final Position delta;

    Direction(final Position delta) {
        this.delta = delta;
    }

    /**
     * Gets the delta position associated with the direction.
     *
     * @return the delta position
     */
    public Position getDelta() {
        return this.delta;
    }
}
