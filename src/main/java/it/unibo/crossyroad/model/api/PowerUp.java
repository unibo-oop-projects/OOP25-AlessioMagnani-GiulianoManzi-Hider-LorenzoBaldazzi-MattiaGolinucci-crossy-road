package it.unibo.crossyroad.model.api;

public interface PowerUp extends Pickable {
    void update(long deltaTime, GameParameters g);
    long getRemaining();
}
