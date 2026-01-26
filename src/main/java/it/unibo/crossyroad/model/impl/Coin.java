package it.unibo.crossyroad.model.impl;

import it.unibo.crossyroad.model.api.AbstractPickable;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.GameParameters;
import it.unibo.crossyroad.model.api.Position;

public class Coin extends AbstractPickable {

    public Coin(final Position position) {
        super(position);
        pickedUp = false;
    }

    @Override
    public void pickUp(final GameParameters g) {
        if (!pickedUp) {
            pickedUp = true;
            g.setCoinCount(g.getCoinCount() + g.getCoinMultiplier());
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.COIN;
    }
}
