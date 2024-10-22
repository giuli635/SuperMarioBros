package entities;

import game.Game;

public abstract class BaseUpdatableEntity extends BaseEntity implements UpdatableEntity {
    protected boolean loaded;
    
    public void load() {
        Game.instance().registerToUpdate(this);
        this.getGraphicElement().loadSprites("mode1");
    }

    public void unload() {
        Game.instance().unregisterToUpdate(this);
    }

    public boolean loaded() {
        return loaded;
    }
}
