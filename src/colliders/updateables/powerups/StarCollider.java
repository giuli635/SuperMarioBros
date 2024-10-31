package colliders.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.BouncerCollider;
import collisions.Collision;
import collisions.updateables.powerups.StarCollision;
import entities.updateables.powerups.Star;
import utils.Axis;

public class StarCollider extends PowerUpCollider implements BouncerCollider {
    protected Star s;

    public StarCollider(Star star, Rectangle b) {
        super(b);
        s = star;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public StarCollision getCollision() {
        return new StarCollision(this);
    }

    @Override
    public Star getEntity() {
        return s;
    }
}
