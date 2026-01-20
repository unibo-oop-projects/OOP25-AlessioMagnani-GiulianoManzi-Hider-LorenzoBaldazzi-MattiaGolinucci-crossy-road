package it.unibo.crossyroad.model;

import it.unibo.crossyroad.model.api.Direction;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.impl.PositionablePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link PositionablePlayer} class.
 */
class TestPositionablePlayer {
    private static final Position INITIAL_POSITION = new Position(10, 2);

    private PositionablePlayer player;

    /**
     * Sets up a new PositionablePlayer instance before each test.
     */
    @BeforeEach
    void setUp() {
        this.player = new PositionablePlayer(INITIAL_POSITION);
    }

    /**
     * Tests that the initial position of the PositionablePlayer is set correctly.
     */
    @Test
    void testInitialPosition() {
        assertEquals(INITIAL_POSITION, this.player.getPosition());
    }

    /**
     * Tests the move method of the PositionablePlayer class, moving the player up.
     */
    @Test
    void testMoveUp() {
        this.player.move(Direction.UP);
        assertEquals(new Position(10, 1), this.player.getPosition());
    }

    /**
     * Tests the move method of the PositionablePlayer class, moving the player down.
     */
    @Test
    void testMoveDown() {
        this.player.move(Direction.DOWN);
        assertEquals(new Position(10, 3), this.player.getPosition());
    }

    /**
     * Tests the move method of the PositionablePlayer class, moving the player left.
     */
    @Test
    void testMoveLeft() {
        this.player.move(Direction.LEFT);
        assertEquals(new Position(9, 2), this.player.getPosition());
    }

    /**
     * Tests the move method of the PositionablePlayer class, moving the player right.
     */
    @Test
    void testMoveRight() {
        this.player.move(Direction.RIGHT);
        assertEquals(new Position(11, 2), this.player.getPosition());
    }

    /**
     * Tests multiple moves of the PositionablePlayer class.
     */
    @Test
    void testMultipleMoves() {
        this.player.move(Direction.UP);
        this.player.move(Direction.LEFT);
        this.player.move(Direction.DOWN);
        this.player.move(Direction.RIGHT);
        assertEquals(INITIAL_POSITION, this.player.getPosition());
    }
}
