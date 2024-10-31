package colliders.updateables.mario;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.mario.Mario;
import utils.Axis;

public class SuperMarioCollider extends MarioCollider {
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
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}

