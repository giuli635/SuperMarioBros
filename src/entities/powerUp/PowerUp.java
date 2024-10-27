package entities.powerUp;

import entities.Entity;
import entities.UpdatableEntity;
import entities.mario.MarioState;

public interface PowerUp extends UpdatableEntity {
    public MarioState getState();
    public int getPoints();
    public Entity clone();
    public void update();
    public void switchDirection();
}
