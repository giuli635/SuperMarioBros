package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.SpinyCollision;
import entities.Spiny;

public class SpinyCollider extends BaseCollider {
    protected Spiny spiny;

    public SpinyCollider(Spiny s, Rectangle b) {
        super(b);
        spiny = s;
    }

    @Override
    public Spiny getEntity() {
        return spiny;
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
