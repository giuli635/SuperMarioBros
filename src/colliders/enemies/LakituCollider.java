package colliders.enemies;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.LakituCollision;
import entities.enemies.Lakitu;

public class LakituCollider extends EnemyCollider {
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
    public LakituCollision getCollision() {
        return new LakituCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}
