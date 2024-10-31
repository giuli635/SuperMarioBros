package colliders;

import java.awt.Rectangle;

import colliders.updateables.BouncerCollider;
import collisions.Collision;
import collisions.updateables.FireBallCollision;
import entities.updateables.FireBall;
import utils.Axis;

public class FireBallCollider extends BaseCollider implements BouncerCollider {
    protected FireBall fireBall;

    public FireBallCollider(FireBall f, Rectangle b) {
        super(b);
        fireBall = f;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public FireBallCollision getCollision() {
        return new FireBallCollision(this);
    }

    @Override
    public FireBall getEntity() {
        return fireBall;
    }
}
