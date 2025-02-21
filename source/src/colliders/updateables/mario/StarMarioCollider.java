package colliders.updateables.mario;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.StarMarioCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.StarMario;
import utils.Axis;

public class StarMarioCollider extends MarioCollider {
    protected Mario mario;
    public static final int PRIORITY = 2;

    public StarMarioCollider(Mario m, StarMario starMario) {
        super(new Rectangle());
        mario = m;
        priority = PRIORITY;
        associatedState = starMario;
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public StarMarioCollision getCollision() {
        MarioCollision collision = baseCollider.getCollision();
        collision.setCollider(this);
        return new StarMarioCollision(this, collision);
    }
}
