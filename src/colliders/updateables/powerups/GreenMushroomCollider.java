package colliders.updateables.powerups;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.powerups.GreenMushroomCollision;
import entities.updateables.powerups.GreenMushroom;
import utils.Axis;

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
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public GreenMushroomCollision getCollision() {
        return new GreenMushroomCollision(this);
    }
}
