package colliders.solids;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.solids.BrickCollision;
import entities.solids.Brick;
import utils.Axis;

public class BrickCollider extends SolidCollider {
    protected Brick brick;

    public BrickCollider(Brick brick2, Rectangle b) {
        super(b);
        brick = brick2;
    }

    public Brick getEntity() {
        return brick;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public BrickCollision getCollision() {
        return new BrickCollision(this);
    }
}
