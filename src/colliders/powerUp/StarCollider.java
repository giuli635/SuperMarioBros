package colliders.powerUp;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.StarCollision;
import entities.powerUp.Star;


public class StarCollider extends PowerUpCollider {
    protected Star s;

    public StarCollider(Star star, Rectangle b) {
        super(b);
        s = star;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new StarCollision(this);
    }

    @Override
    public Star getEntity() {
        return s;
    }

}
