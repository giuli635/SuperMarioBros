package entities.updateables;

import entities.Body;
import game.Game;

public abstract class UpdateableBody extends Body implements UpdatableEntity {
    protected boolean loaded;
    
    public void load() {
        Game.instance().registerToUpdate(this);
        loaded = true;
    }

    public void unload() {
        Game.instance().unregisterToUpdate(this);
        loaded = false;
    }

    public boolean loaded() {
        return loaded;
    }
}
