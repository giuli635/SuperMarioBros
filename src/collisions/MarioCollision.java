package collisions;

import colliders.MarioCollider;

public interface MarioCollision extends UpdateableEntityCollision {
    public MarioCollider getCollider();
}

