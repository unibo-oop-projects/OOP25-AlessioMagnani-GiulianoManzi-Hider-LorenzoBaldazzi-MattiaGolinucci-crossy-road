package it.unibo.crossyroad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.impl.GameParametersImpl;
import it.unibo.crossyroad.model.impl.SlowCars;

/**
 * Tests class for the {@link SlowCars} class.
 */
class TestSlowCars {
    private static final int POS = 0;
    private static final double CAR_SLOW_MOTION = 0.5;
    private static final long CAR_SLOW_MOTION_DURATION = 10_000L;
    private SlowCars slowCars; 
    private GameParameters gameParameters;

    /**
     * Sets up a new slow cars power-up and a new instance of game parameters.
     */
    @BeforeEach
    void setUp() {
        this.slowCars = new SlowCars(new Position(POS, POS));
        this.gameParameters = new GameParametersImpl();
    }

    /**
     * Tests that a slow cars power-up isn't picked up at the begining.
     */
    @Test
    void testNotPickedUpAtTheBeginning() {
        assertFalse(this.slowCars.isPickUp());
    }

    /**
     * Tests that the slow cars power-up is marked as picked.
     */
    @Test
    void testPowerUpPickedUp() {
        this.slowCars.pickUp(this.gameParameters);
        assertTrue(this.slowCars.isPickUp());
    }

    /**
     * Tests that the power-up applies the slow cars.
     */
    @Test
    void testApplyEffectSlowCars() {
        slowCars.pickUp(this.gameParameters);
        assertEquals(CAR_SLOW_MOTION, this.gameParameters.getCarSpeedMultiplier());
    }

    /**
     * Tests that slow cars power-up effect is applied only once.
     */
    @Test
    void testSlowCarsPowerUpPickedUpOnlyOnce() {
        this.slowCars.pickUp(this.gameParameters);
        this.slowCars.pickUp(this.gameParameters);
        assertEquals(CAR_SLOW_MOTION, this.gameParameters.getCarSpeedMultiplier());
    }

    /**
     * Tests that the slow cars power-up terminates after his duration.
     */
    @Test
    void testSlowCarsPowerUpDeactiveAfterDuration() {
        this.slowCars.pickUp(this.gameParameters);
        this.slowCars.update(CAR_SLOW_MOTION_DURATION, this.gameParameters);
        assertEquals(1.0, this.gameParameters.getCarSpeedMultiplier());
    }

    /**
     * Tests that the slow cars power-up doesn't terminate before its duration.
     */
    @Test
    void testSlowCarsPowerUpNotDeactiveBeforeDuration() {
        this.slowCars.pickUp(this.gameParameters);
        this.slowCars.update(CAR_SLOW_MOTION_DURATION / 3, this.gameParameters);
        assertEquals(CAR_SLOW_MOTION, this.gameParameters.getCarSpeedMultiplier());
    }
}
