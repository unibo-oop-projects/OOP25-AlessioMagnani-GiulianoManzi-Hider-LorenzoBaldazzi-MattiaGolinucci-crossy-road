package it.unibo.crossyroad.model.impl;

import it.unibo.crossyroad.model.api.AbstractPowerUp;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;

public class Invincibility extends AbstractPowerUp {

    private static final long INVINCIBILITY_DURATION = 10_000L;

    public Invincibility(final Position position){
        super(position,INVINCIBILITY_DURATION);
    }

    @Override
    public void deactivate(final GameParameters g) {        
        g.setInvincibility(false);
    }

    @Override
    public void pickUp(final GameParameters g) {
        if (!pickedUp) {
            pickedUp = true;
            g.setInvincibility(true);
        }
    }

    public EntityType getEntityType() {
        return EntityType.INVINCIBILITY; 
    }
}
