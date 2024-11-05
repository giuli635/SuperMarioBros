package colliders.updateables.mario;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.SuperMario;
import utils.Axis;

public class SuperMarioCollider extends MarioCollider {
    public static final int PRIORITY = 0;

    public SuperMarioCollider(Mario m, Rectangle b, SuperMario s) {
        super(b);
        mario = m;
        priority = PRIORITY;
        associatedState = s;
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

