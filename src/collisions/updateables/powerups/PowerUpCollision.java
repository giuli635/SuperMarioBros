package collisions.updateables.powerups;

import colliders.updateables.powerups.PowerUpCollider;
import collisions.updateables.UpdateableEntityCollision;

public interface PowerUpCollision extends UpdateableEntityCollision{
    public PowerUpCollider getCollider();
}
