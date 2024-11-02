package colliders.updateables.mario;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.FireMarioCollision;
import entities.updateables.mario.FireMario;
import entities.updateables.mario.Mario;
import utils.Axis;

public class FireMarioCollider extends SuperMarioCollider {
    public static final int PRIORITY = 0;

    public FireMarioCollider(Mario m, Rectangle b, FireMario f) {
        super(m, b, f);
        mario = m;
        priority = PRIORITY;
    }

    @Override
    public FireMarioCollision getCollision() {
        return new FireMarioCollision(this);
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}

