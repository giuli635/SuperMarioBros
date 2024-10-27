package colliders.powerUp;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.FireFlowerCollision;
import entities.powerUp.FireFlower;


public class FireFlowerCollider extends PowerUpCollider {
    protected FireFlower f;

    public FireFlowerCollider(FireFlower fireFlower, Rectangle b) {
        super(b);
        f = fireFlower;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new FireFlowerCollision(this);
    }    

    @Override
    public FireFlower getEntity() {
       return f;
    }

}
