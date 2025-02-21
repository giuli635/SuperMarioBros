package graphics;

import game.SingletonGraphicEngine;

public abstract class BaseGraphicElement implements GraphicElement {
    protected boolean added;

    @Override
    public boolean added() {
        return added;
    }

    @Override
    public void add() {
        SingletonGraphicEngine.instance().add(this);
    }

    @Override
    public void remove() {
        SingletonGraphicEngine.instance().remove(this);
    }

    @Override
    public void setAdded(boolean status) {
        added = status;
    }
}
