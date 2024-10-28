package colliders;

import collisions.MarioCollision;
import entities.mario.Mario;

public interface MarioCollider extends UpdateableEntityCollider {
    public Mario getEntity();
    public MarioCollision getCollision();
}

