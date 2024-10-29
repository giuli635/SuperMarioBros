package collisions.updateables;

import colliders.updateables.UpdateableEntityCollider;
import collisions.Collision;

public interface UpdateableEntityCollision extends Collision {
    public UpdateableEntityCollider getCollider();
}
