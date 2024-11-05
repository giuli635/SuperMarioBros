package colliders.updateables;

import entities.updateables.MovableEntity;

public interface MovableEntityCollider extends UpdateableEntityCollider {
    public MovableEntity getEntity();
}
