package colliders;

import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.BrickCollision;
import collisions.Collision;
import entities.Brick;

public class BrickCollider extends SolidCollider {
    protected Brick brick;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public BrickCollider(Brick e, Rectangle b) {
        super(b);
        brick = e;
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
