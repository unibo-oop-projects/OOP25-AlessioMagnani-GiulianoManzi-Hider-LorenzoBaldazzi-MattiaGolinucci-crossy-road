package it.unibo.crossyroad.model.api;

public abstract class AbstractPowerUp extends AbstractPickable implements PowerUp {
    protected long remainingTime;

    public AbstractPowerUp(final Position position,final long remainingTime){
        super(position);
        this.remainingTime = remainingTime;
    }

    @Override
    public void update(long deltaTime, GameParameters g) {
        remainingTime -= deltaTime;
        if(remainingTime <= 0){
            this.deactivate(g);
        }  
    }

    @Override
    public long getRemaining() {
        return this.remainingTime;
    }

    protected abstract void deactivate(GameParameters g);
}
