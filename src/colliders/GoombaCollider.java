package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.GoombaCollision;
import collisions.MarioCollision;
import entities.Goomba;

public class GoombaCollider extends BaseCollider implements UpdateableEntityCollider {
    protected Goomba goomba;

    public GoombaCollider(Goomba g, Rectangle b) {
        super(b);
        goomba = g;
    }

    @Override
    public Goomba getEntity() {
        return goomba;
    }

    @Override
    public Collision getCollision() {
        return new GoombaCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        if(collisionDirection == Direction.DOWN) {
            goomba.recieveDamage();
            //m.getCollider().getEntity().addPoints(60);
            m.getCollider().getEntity().addVelocity(0, 8);
        } else {
            m.getCollider().getEntity().die();
        }
    }
}
