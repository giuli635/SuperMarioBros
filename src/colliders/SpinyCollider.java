package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.SpinyCollision;
import entities.Enemy;
import entities.Entity;
import entities.Spiny;

public class SpinyCollider extends BaseCollider {
    protected Enemy spiny;

    public SpinyCollider(Enemy s, Rectangle b) {
        super(b);
        spiny = s;
    }

    @Override
    public Entity getEntity() {
        return spiny;
    }

    public Spiny getSpiny() {
        return  (Spiny) spiny;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new SpinyCollision(this);
    }
}
