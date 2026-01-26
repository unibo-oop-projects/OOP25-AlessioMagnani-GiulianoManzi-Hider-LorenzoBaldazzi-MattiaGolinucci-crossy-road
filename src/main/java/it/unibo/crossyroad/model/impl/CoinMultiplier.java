package it.unibo.crossyroad.model.impl;

import it.unibo.crossyroad.model.api.AbstractPowerUp;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;

public class CoinMultiplier extends AbstractPowerUp {

    private static final int COIN_MULTIPLIER = 3;
    private static final long COIN_MULTIPLIER_DURATION = 10_000L;

    public CoinMultiplier(final Position position) {
        super(position, COIN_MULTIPLIER_DURATION);
        pickedUp = false;
    }

    @Override
    protected void deactivate(final GameParameters g) {
        g.setCoinMultiplier(1);
    }

    @Override
    public void pickUp(final GameParameters g) {
        if (!pickedUp) {
            pickedUp = true;
            g.setCoinMultiplier(g.getCoinMultiplier() * COIN_MULTIPLIER);
        }
    }

    public EntityType getEntityType() {
        return EntityType.COIN_MULTIPLIER; 
    }
}
