package collisions;

import colliders.Direction;
import colliders.GameCollider;
import colliders.MarioCollider;
import colliders.ScreenDisplacementCollider;

public class ScreenDisplacementCollision implements Collision {
    protected ScreenDisplacementCollider collider;

    public ScreenDisplacementCollision(ScreenDisplacementCollider c) {
        collider = c;
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
    }
}
