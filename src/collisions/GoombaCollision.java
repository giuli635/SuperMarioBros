package collisions;

import colliders.Collider;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.PowerUpCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;

public class GoombaCollision implements Collision {
    protected GoombaCollider GC;

    public GoombaCollision(GoombaCollider GoombaC){
        GC=GoombaC;
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
    public GoombaCollider getCollider() {
        return GC;
    }
    @Override
    public void collide(PowerUpCollider c) {
        c.handleCollision(this);
    }


}
