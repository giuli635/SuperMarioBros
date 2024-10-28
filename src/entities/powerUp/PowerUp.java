package entities.powerUp;

import entities.Entity;
import entities.UpdatableEntity;

public interface PowerUp extends UpdatableEntity {
    public int getPoints();
    public Entity clone();
    public void update();
    public void switchDirection();
}
