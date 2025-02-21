package entities.updateables;

import entities.Body;
import game.SingletonGame;

public abstract class UpdateableBody extends Body implements ObserverUpdateableEntity {
    protected boolean loaded;
    
    public void load() {
        SingletonGame.instance().registerToUpdate(this);
        loaded = true;
    }

    public void unload() {
        SingletonGame.instance().unregisterToUpdate(this);
        loaded = false;
    }

    public boolean loaded() {
        return loaded;
    }
}
