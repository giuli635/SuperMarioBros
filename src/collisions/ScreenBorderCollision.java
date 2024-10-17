package collisions;

import colliders.Collider;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public class ScreenBorderCollision implements Collision {
    protected ScreenBorderCollider collider;

    public ScreenBorderCollision(ScreenBorderCollider c) {
        collider = c;
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
    public Collider getCollider() {
        return collider;
    }
}
