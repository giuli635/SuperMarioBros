package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.SuperMarioCollision;
import entities.mario.Mario;

public class SuperMarioCollider extends BaseCollider implements MarioCollider {
    protected Mario mario;

    public SuperMarioCollider(Mario m, Rectangle b) {
        super(b);
        mario = m;
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    @Override
    public SuperMarioCollision getCollision() {
        return new SuperMarioCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}

