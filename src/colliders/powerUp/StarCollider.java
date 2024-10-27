package colliders.powerUp;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.UpdateableEntityCollider;
import collisions.Axis;
import collisions.Collision;
import entities.powerUp.Star;
import entities.UpdatableEntity;

public class StarCollider extends BaseCollider implements UpdateableEntityCollider {
    protected Star s;

    public StarCollider(Star star, Rectangle b) {
        super(b);
        s = star;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendCollision'");
    }

    @Override
    public Collision getCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollision'");
    }

    @Override
    public UpdatableEntity getEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEntity'");
    }

}
