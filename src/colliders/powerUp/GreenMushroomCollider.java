package colliders.powerUp;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.GreenMushroomCollision;
import entities.powerUp.GreenMushroom;

public class GreenMushroomCollider extends PowerUpCollider {
    protected GreenMushroom g;

    public GreenMushroomCollider(GreenMushroom greenMushroom, Rectangle b) {
        super(b);
        g = greenMushroom;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new GreenMushroomCollision(this);
    }    

    @Override
    public GreenMushroom getEntity() {
       return g;
    }

}
