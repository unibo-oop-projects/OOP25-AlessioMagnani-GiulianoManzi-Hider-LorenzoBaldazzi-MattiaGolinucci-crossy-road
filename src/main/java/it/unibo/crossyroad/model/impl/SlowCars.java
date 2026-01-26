package it.unibo.crossyroad.model.impl;

import it.unibo.crossyroad.model.api.AbstractPowerUp;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;

public class SlowCars extends AbstractPowerUp{

    private static final double CAR_SLOW_MOTION = 0.5;
    private static final long CAR_SLOW_MOTION_DURATION = 10_000L;

    public SlowCars(final Position position) {
        super(position,CAR_SLOW_MOTION_DURATION);
        pickedUp = false;
    }

    @Override
    public void deactivate(final GameParameters g) {
        g.setCarSpeedMultiplier(1.0);
    }

    @Override
    public void pickUp(final GameParameters g) {
        if (!pickedUp) {
            pickedUp = true;
            g.setCarSpeedMultiplier(g.getCarSpeedMultiplier() * CAR_SLOW_MOTION);
        }
    }

    public EntityType getEntityType() {
        return EntityType.SLOW_CARS; 
    }
}