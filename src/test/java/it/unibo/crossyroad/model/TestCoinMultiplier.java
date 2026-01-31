
package it.unibo.crossyroad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.impl.CoinMultiplier;
import it.unibo.crossyroad.model.impl.GameParametersImpl;

/**
 * Test class for the {@link CoinMultiplier} class. 
 */
class TestCoinMultiplier {
    private static final int POS = 0;
    private static final int COIN_MULTIPLIER = 3;
    private static final long COIN_MULTIPLIER_DURATION = 10_000L;
    private CoinMultiplier coinMultiplier;
    private GameParameters gameParameters;

    /**
     * Sets up a new coin multiplier power-up and a new instance of game parameters.
     */
    @BeforeEach
    void setUp() {
        this.coinMultiplier = new CoinMultiplier(new Position(POS, POS));
        this.gameParameters = new GameParametersImpl();
    }

    /**
     * Tests that a coin multiplier power-up isn't picked up at the begining.
     */
    @Test
    void testNotPickedUpAtTheBeginning() {
        assertFalse(this.coinMultiplier.isPickUp());
    }

    /**
     * Tests that the coin multiplier power-up is marked as picked.
     */
    @Test
    void testPowerUpPickedUp() {
        this.coinMultiplier.pickUp(this.gameParameters);
        assertTrue(this.coinMultiplier.isPickUp());
    }

    /**
     * Tests that the power-up applies the coin multiplier.
     */
    @Test
    void testApplyEffectCoinMultiplier() {
        this.coinMultiplier.pickUp(this.gameParameters);
        assertEquals(COIN_MULTIPLIER, this.gameParameters.getCoinMultiplier());
    }

    /**
     * Tests that coin multiplier power-up effect is applied only once.
     */
    @Test
    void testCoinMultiplierPowerUpPickedUpOnlyOnce() {
        this.coinMultiplier.pickUp(this.gameParameters);
        this.coinMultiplier.pickUp(this.gameParameters);
        assertEquals(COIN_MULTIPLIER, this.gameParameters.getCoinMultiplier());
    }

    /**
     * Tests that the coin multiplier power-up terminates after his duration.
     */
    @Test
    void testCoinMultiplierPowerUpDeactiveAfterDuration() {
        this.coinMultiplier.pickUp(this.gameParameters);
        this.coinMultiplier.update(COIN_MULTIPLIER_DURATION, this.gameParameters);
        assertEquals(1, this.gameParameters.getCoinMultiplier());
    }

    /**
     * Tests that the coin multiplier power-up doesn't terminate before its duration.
     */
    @Test
    void testCoinMultiplierPowerUpNotDeactiveBeforeDuration() {
        this.coinMultiplier.pickUp(this.gameParameters);
        this.coinMultiplier.update(COIN_MULTIPLIER_DURATION / 3, this.gameParameters);
        assertEquals(COIN_MULTIPLIER, this.gameParameters.getCoinMultiplier());
    }
}
