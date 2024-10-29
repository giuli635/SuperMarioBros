package colliders.updateables.mario;

import colliders.updateables.UpdateableEntityCollider;
import collisions.updateables.mario.MarioCollision;
import entities.updateables.mario.Mario;

public interface MarioCollider extends UpdateableEntityCollider {
    public Mario getEntity();
    public MarioCollision getCollision();
}

