package colliders.updateables;

import colliders.Collider;
import entities.updateables.UpdateableEntity;

public interface UpdateableEntityCollider extends Collider {
    public UpdateableEntity getEntity();
}
