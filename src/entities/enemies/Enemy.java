package entities.enemies;

import entities.Entity;
import entities.UpdatableEntity;

public interface Enemy extends UpdatableEntity {
    public void recieveDamage();
    public int getPoints();
    public Entity clone();
    public void update();
    public void switchDirection();
}
