package it.unibo.crossyroad.model;

import it.unibo.crossyroad.model.api.*;
import it.unibo.crossyroad.model.impl.Car;
import it.unibo.crossyroad.model.impl.GameParametersBuilder;
import it.unibo.crossyroad.model.impl.Road;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestRoad {
    private static final long SPAWN_CAR_INTERVAL_MS = 1200;

    private Road road;

    /**
     * Sets up a new Road instance before each test.
     */
    @BeforeEach
    void setUp() {
        this.road = new Road(new Position(0, 0), new Dimension(10,3));
    }

    /**
     * Test to verify that the entity type of the road is correctly identified as ROAD.
     */
    @Test
    void testEntityType() {
        assertEquals(EntityType.ROAD, this.road.getEntityType());
    }


    @Test
    void testRandomCarGeneration() {
        int leftCars = 0;
        int rightCars = 0;
        final GameParameters gp = new GameParametersBuilder()
                .setCarSpeedMultiplier(1)
                .build();
        for (int i = 0; i < 20; i++) {
            this.road.update(gp, 500);
        }
        final int cars = road.getObstacles().size();
        for (var obs: road.getObstacles()) {
            final double carX = obs.getPosition().x();
            final double chunkX = road.getPosition().x();
            final double chunkWidth = road.getDimension().width();
            if (carX < chunkX) {
                rightCars++;
            } else if (carX > chunkX + chunkWidth) {
                leftCars++;
            }
        }

        assertTrue(leftCars > 0);
        assertTrue(rightCars > 0);
    }

}
