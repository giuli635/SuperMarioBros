package collisions;

import colliders.powerUp.PowerUpCollider;

public interface PowerUpCollision extends UpdateableEntityCollision{
    public PowerUpCollider getCollider();
}
