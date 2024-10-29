package entities.updateables.powerups;

import entities.updateables.MovableEntity;
import entities.updateables.UpdatableEntity;

public interface PowerUp extends UpdatableEntity, MovableEntity {
    public static final String SOUND = "powerup.wav";
}
