package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.LakituCollision;
import entities.Lakitu;

public class LakituCollider extends BaseCollider implements UpdateableEntityCollider {
    protected Lakitu lakitu;

    public LakituCollider(Lakitu l, Rectangle b) {
        super(b);
        lakitu = l;
    }

    @Override
    public Lakitu getEntity() {
        return lakitu;
    }

    @Override
    public Collision getCollision() {
        return new LakituCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}
