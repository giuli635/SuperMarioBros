package colliders.updateables;

import colliders.Collider;
import entities.updateables.ObserverUpdateableEntity;

public interface UpdateableEntityCollider extends Collider {
    public ObserverUpdateableEntity getEntity();
}
