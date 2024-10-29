package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.enemies.LakituCollision;
import entities.updateables.enemies.Lakitu;
import utils.Axis;

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
