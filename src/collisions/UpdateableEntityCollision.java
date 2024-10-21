package collisions;

import colliders.UpdateableEntityCollider;

public interface UpdateableEntityCollision extends Collision {
    public UpdateableEntityCollider getCollider();
}
