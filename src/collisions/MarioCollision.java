package collisions;

import colliders.Direction;
import colliders.GameCollider;
import colliders.MarioCollider;
import colliders.ScreenDisplacementCollider;

public class MarioCollision implements Collision {
    protected MarioCollider collider;

    public MarioCollision(MarioCollider m) {
        collider = m;
    }

    @Override
    public void collide(MarioCollider c, Direction d) {
        c.handleCollision(this, d);
    }

    @Override
    public void collide(GameCollider c, Direction d) {
        c.handleCollision(this, d);
    }

    @Override
    public void collide(ScreenDisplacementCollider c, Direction d) {
        c.handleCollision(this, d);
    }

    public MarioCollider getCollider() {
        return collider;
    }
}

