package collisions;

import colliders.Collider;
import colliders.Direction;
import colliders.GameCollider;
import colliders.GoombaCollider;
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
    public void collide(PowerUpCollider c) {
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
