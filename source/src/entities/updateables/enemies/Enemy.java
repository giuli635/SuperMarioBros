package entities.updateables.enemies;

import entities.updateables.MovableEntity;

public interface Enemy extends MovableEntity {
    public void recieveDamage();
    public int pointsToAdd();
    public int pointsToSubtract();
}
