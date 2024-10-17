package collisions;

import colliders.Direction;
import colliders.GameCollider;
import colliders.MarioCollider;
import colliders.PowerUpCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public class PowerUpCollision implements Collision {
    protected PowerUpCollider collider;

    public PowerUpCollision(PowerUpCollider pw) {
        collider = pw;
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

    @Override
    public void collide(ScreenBorderCollider c, Direction d) {
        c.handleCollision(this, d);
    }

    @Override
    public void collide(PowerUpCollider c, Direction d) {
        c.handleCollision(this, d);
    }

    


}
