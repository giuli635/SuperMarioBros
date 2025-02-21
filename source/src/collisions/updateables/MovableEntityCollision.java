package collisions.updateables;

import colliders.updateables.MovableEntityCollider;

public interface MovableEntityCollision extends UpdateableEntityCollision {
    public MovableEntityCollider getCollider();
}
