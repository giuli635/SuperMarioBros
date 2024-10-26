package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.LakituCollision;
import collisions.MarioCollision;
import entities.Lakitu;

public class LakituCollider extends BaseCollider implements EnemyCollider {
    protected Lakitu lakitu;

    public LakituCollider(Lakitu l, Rectangle b) {
        super(b);
        lakitu = l;
    }

    @Override
    public Lakitu getEntity() {
        return lakitu;
    }

    @Override
    public Collision getCollision() {
        return new LakituCollision(this);
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
        if(collisionDirection == Direction.DOWN && m.getCollider().getEntity().getJumping()) { //TODO: make this more robust
            lakitu.recieveDamage();
            m.getCollider().getEntity().addVelocity(0, 8);
        } else {
            m.getCollider().getEntity().die();
        }
    }
}
