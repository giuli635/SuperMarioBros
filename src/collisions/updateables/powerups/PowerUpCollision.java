package collisions.updateables.powerups;

import colliders.updateables.powerups.PowerUpCollider;
import collisions.updateables.MovableEntityCollision;

public interface PowerUpCollision extends MovableEntityCollision {
    public PowerUpCollider getCollider();
}
