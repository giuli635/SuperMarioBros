package collisions;

import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.KoopaTroopaCollider;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SpinyCollider;
import colliders.SuperMushroomCollider;

public class SuperMushroomCollision implements Collision {
    protected SuperMushroomCollider collider;


    public SuperMushroomCollision(SuperMushroomCollider c) {
        collider = c;
    }

    @Override
    public SuperMushroomCollider getCollider() {
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
    public void collide(SpinyCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(KoopaTroopaCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }

    @Override
    public void collide(SuperMushroomCollider c, Axis a) {
        if (a == Axis.X) {
            c.handleHorizontalCollision(this);
        } else {
            c.handleVerticalCollision(this);
        }
    }
    
}
