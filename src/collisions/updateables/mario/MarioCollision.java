package collisions.updateables.mario;

import colliders.updateables.mario.MarioCollider;
import collisions.updateables.UpdateableEntityCollision;

public interface MarioCollision extends UpdateableEntityCollision {
    public MarioCollider getCollider();
}

