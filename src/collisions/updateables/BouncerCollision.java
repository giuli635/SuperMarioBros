package collisions.updateables;

import colliders.updateables.BouncerCollider;

public interface BouncerCollision extends UpdateableEntityCollision {
    public BouncerCollider getCollider();
}
