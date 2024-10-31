package colliders.updateables.mario;

import colliders.Collider;
import collisions.Collision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.InvulnerableCollision;
import entities.updateables.mario.Mario;
import utils.Axis;

public class InvulnerableCollider extends MarioCollider {
    protected Mario mario;
    protected MarioCollider marioCollider;

    public InvulnerableCollider(Mario m) {
        super(m.getCollider());
        marioCollider = m.getCollider();
        mario = m;
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
        MarioCollision collision = marioCollider.getCollision();
        collision.setCollider(this);
        return new InvulnerableCollision(this, collision);
    }
}
