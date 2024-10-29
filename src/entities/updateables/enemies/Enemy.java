package entities.updateables.enemies;

import entities.updateables.MovableEntity;
import entities.updateables.UpdatableEntity;

public interface Enemy extends UpdatableEntity, MovableEntity {
    public void recieveDamage();
}
