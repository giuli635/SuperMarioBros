package colliders.powerUp;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.SuperMushroomCollision;
import entities.powerUp.SuperMushroom;


public class SuperMushroomCollider extends PowerUpCollider {
    protected SuperMushroom m;

    public SuperMushroomCollider(SuperMushroom mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public SuperMushroom getEntity() {
        return m;
    }

    public SuperMushroom getSuperMushroom() {
        return (SuperMushroom) m;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public SuperMushroomCollision getCollision() {
        return new SuperMushroomCollision(this);
    }

}
