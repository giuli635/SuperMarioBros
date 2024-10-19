package collisions;

import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.PowerUpCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SpinyCollider;

public class MarioCollision implements Collision {
    protected MarioCollider collider;

    public MarioCollision(MarioCollider m) {
        collider = m;
    }

    public MarioCollider getCollider() {
        return collider;
    }

    @Override
    public void collide(MarioCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(GameCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(ScreenDisplacementCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(ScreenBorderCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(GoombaCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(PowerUpCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(SpinyCollider c) {
        c.handleCollision(this);
    }
}

