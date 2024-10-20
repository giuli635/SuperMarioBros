package collisions;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.PowerUpCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SpinyCollider;

public class GameCollision implements Collision {
    protected GameCollider collider;

    public GameCollision(GameCollider c) {
        collider = c;
    }

    public GameCollider getCollider() {
        return collider;
    }

    @Override
    public void collide(MarioCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(GameCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(ScreenDisplacementCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(ScreenBorderCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(GoombaCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(PowerUpCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(SpinyCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }
}
