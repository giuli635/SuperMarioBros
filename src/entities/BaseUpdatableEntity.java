package entities;

import game.Game;

public abstract class BaseUpdatableEntity implements UpdatableEntity {
    protected boolean loaded;
    
    public void load() {
        loaded = true;
        Game.instance().registerToUpdate(this);
    }

    public void unload() {
        loaded = false;
        Game.instance().unregisterToUpdate(this);
    }

    public boolean loaded() {
        return loaded;
    }
}
