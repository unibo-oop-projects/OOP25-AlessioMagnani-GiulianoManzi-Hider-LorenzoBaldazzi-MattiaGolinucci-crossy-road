package it.unibo.crossyroad.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.impl.GameParametersImpl;
import it.unibo.crossyroad.model.impl.Invincibility;

/**
 * Test class for the {@link Invincibility} class.
 */
class TestInvincibility {
    private static final int POS = 0;
    private static final long INVINCIBILITY_DURATION = 10_000L;
    private Invincibility invincibility;
    private GameParameters gameParameters;

    /**
     * Sets up a new invincibility power-up and a new instance of game parameters.
     */
    @BeforeEach
    void setUp() {
        this.invincibility = new Invincibility(new Position(POS, POS));
        this.gameParameters = new GameParametersImpl();
    }

    /**
     * Tests that an inivincibility power-up isn't picked up at the beginning.
     */
    @Test
    void testNotPickedUpAtTheBeginning() {
        assertFalse(this.invincibility.isPickUp());
    }

    /**
     * Tests that the invincibility power-up is marked as picked.
     */
    @Test
    void testPowerUpPickedUp() {
        this.invincibility.pickUp(this.gameParameters);
        assertTrue(this.invincibility.isPickUp());
    }

    /**
     * Tests that the power-up applies the invincibility.
     */
    @Test
    void testApplyEffectInvicibility() {
        this.invincibility.pickUp(this.gameParameters);
        assertTrue(this.gameParameters.isInvincible());
    }

    /**
     * Tests that the invincibility power-up effect is applied only once.
     */
    @Test
    void testInvincibilityPowerUpPickedUpOnlyOnce() {
        this.invincibility.pickUp(this.gameParameters);
        this.invincibility.pickUp(this.gameParameters);
        assertTrue(this.gameParameters.isInvincible());
    }

    /**
     * Tests that the invicibility power-up terminates after his duration.
     */
    @Test
    void testInvincibilityPowerUpDeactiveAfterDuration() {
        this.invincibility.pickUp(this.gameParameters);
        this.invincibility.update(INVINCIBILITY_DURATION, this.gameParameters);
        assertFalse(this.gameParameters.isInvincible());
    }

   /**
    * Tests that the invincibility power-up doesn't terminate before duration.
    */
   @Test
   void testInvicibilityPowerUpNotDeactiveBeforeDuration() {
        this.invincibility.pickUp(this.gameParameters);
        this.invincibility.update(INVINCIBILITY_DURATION / 3, this.gameParameters);
        assertTrue(this.gameParameters.isInvincible());
    }
}
