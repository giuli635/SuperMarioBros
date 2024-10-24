package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SpinyCollision;
import entities.Spiny;

public class SpinyCollider extends BaseCollider implements UpdateableEntityCollider {
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

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }
}
