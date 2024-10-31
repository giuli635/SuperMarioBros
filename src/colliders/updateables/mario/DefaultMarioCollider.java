package colliders.updateables.mario;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.DefaultMarioCollision;
import entities.updateables.mario.Mario;
import utils.Axis;

public class DefaultMarioCollider extends MarioCollider {
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
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}

