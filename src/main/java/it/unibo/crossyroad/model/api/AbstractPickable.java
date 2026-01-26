package it.unibo.crossyroad.model.api;

public abstract class AbstractPickable extends AbstractPositionable implements Pickable {
    
    protected boolean pickedUp;

    public AbstractPickable(Position position){
        super(position, new Dimension(1, 1));
        this.pickedUp = false;
    }

    public boolean isPickUp() {
        return pickedUp;
    }
}