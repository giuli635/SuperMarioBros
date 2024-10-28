package colliders.powerUp;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.GreenMushroomCollision;
import entities.powerUp.GreenMushroom;


public class GreenMushroomCollider extends PowerUpCollider {
    protected GreenMushroom m;

    public GreenMushroomCollider(GreenMushroom mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public GreenMushroom getEntity() {
        return m;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public GreenMushroomCollision getCollision() {
        return new GreenMushroomCollision(this);
    }
}
