package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.DefaultMarioCollision;
import entities.mario.Mario;

public class DefaultMarioCollider extends BaseCollider implements MarioCollider {
    protected Mario mario;

    public DefaultMarioCollider(Mario m, Rectangle b) {
        super(b);
        mario = m;
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    @Override
    public DefaultMarioCollision getCollision() {
        return new DefaultMarioCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}

