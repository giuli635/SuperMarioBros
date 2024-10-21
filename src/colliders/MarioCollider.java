package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import entities.Mario;

public class MarioCollider extends BaseCollider implements UpdateableEntityCollider {
    protected Mario mario;

    public MarioCollider(Mario m, Rectangle b) {
        super(b);
        mario = m;
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    @Override
    public Collision getCollision() {
        return new MarioCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}

