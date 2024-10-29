package entities.updateables;

import entities.Entity;

public interface UpdatableEntity extends Entity {
    public void update();
    public boolean loaded();
    public void load();
    public void unload();
}
