package collisions.updateables;

import colliders.updateables.UpdateableEntityCollider;
import collisions.VisitorCollision;

public interface UpdateableEntityCollision extends VisitorCollision {
    public UpdateableEntityCollider getCollider();
}
