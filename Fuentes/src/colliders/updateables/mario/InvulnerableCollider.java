package colliders.updateables.mario;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.InvulnerableCollision;
import entities.updateables.mario.Mario;
import utils.Axis;

public class InvulnerableCollider extends MarioCollider {
    protected Mario mario;
    public static final int PRIORITY = 1;

    public InvulnerableCollider(Mario m) {
        super(new Rectangle());
        mario = m;
        priority = PRIORITY;
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public InvulnerableCollision getCollision() {
        MarioCollision collision = baseCollider.getCollision();
        collision.setCollider(this);
        return new InvulnerableCollision(this, collision);
    }
}
