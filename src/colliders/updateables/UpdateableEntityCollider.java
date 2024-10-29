package colliders.updateables;

import colliders.Collider;
import entities.updateables.UpdatableEntity;

public interface UpdateableEntityCollider extends Collider {
    public UpdatableEntity getEntity();
}
