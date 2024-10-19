package collisions;

import colliders.Collider;
import colliders.GameCollider;
import colliders.GoombaCollider;
import colliders.MarioCollider;
import colliders.PowerUpCollider;
import colliders.ScreenBorderCollider;
import colliders.ScreenDisplacementCollider;
import colliders.SpinyCollider;

public class SpinyCollision implements Collision {
    protected SpinyCollider SC;

    public SpinyCollision(SpinyCollider SpinyC) {
        SC = SpinyC;
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
    public void collide(SpinyCollider c) {
        c.handleCollision(this); 
    }
    
    @Override
    public SpinyCollider getCollider() {
        return SC;
    }
    
    public void collide(PowerUpCollider c) {
        c.handleCollision(this);
    }

    @Override
    public void collide(GoombaCollider c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collide'");
    }
}
