package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.enemies.SpinyCollision;
import collisions.updateables.mario.MarioCollision;
import entities.updateables.enemies.Spiny;
import utils.Axis;

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
    public void recieveCollision(Collision c, Axis a) {
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
