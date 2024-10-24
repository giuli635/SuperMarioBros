package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.BuzzyBeetleCollision;
import collisions.MarioCollision;
import entities.BuzzyBeetle;

public class BuzzyBeetleCollider extends BaseCollider implements UpdateableEntityCollider {
    protected BuzzyBeetle buzzyBeetle;

    public BuzzyBeetleCollider(BuzzyBeetle z, Rectangle b) {
        super(b);
        buzzyBeetle = z;
    }

    @Override
    public BuzzyBeetle getEntity() {
        return buzzyBeetle;
    }

    @Override
    public Collision getCollision() {
        return new BuzzyBeetleCollision(this);
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
            buzzyBeetle.recieveDamage();
            m.getCollider().getEntity().addVelocity(0, 8);
        } else {
            m.getCollider().getEntity().die();
        }
    }
}
