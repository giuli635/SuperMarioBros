package graphics;

import game.GraphicEngine;

public abstract class BaseGraphicElement implements GraphicElement {
    protected boolean added;

    @Override
    public boolean added() {
        return added;
    }

    @Override
    public void add() {
        GraphicEngine.instance().add(this);
    }

    @Override
    public void remove() {
        GraphicEngine.instance().remove(this);
    }

    @Override
    public void setAdded(boolean status) {
        added = status;
    }
}
