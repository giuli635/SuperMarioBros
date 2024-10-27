package colliders.enemies;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SpinyCollision;
import entities.enemies.Spiny;

public class SpinyCollider extends EnemyCollider {
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
    public SpinyCollision getCollision() {
        return new SpinyCollision(this);
    }

    @Override
    public void handleVerticalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }
}
