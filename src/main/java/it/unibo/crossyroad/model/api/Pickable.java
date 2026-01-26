package it.unibo.crossyroad.model.api;

public interface Pickable extends Positionable {
    void pickUp(GameParameters g);
    boolean isPickUp();
}
