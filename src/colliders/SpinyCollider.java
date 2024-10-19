package colliders;

import java.awt.Rectangle;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SpinyCollision;
import entities.Enemy;
import entities.Entity;

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

    @Override
    public void sendCollision(Collision c) {
        c.collide(this);
    }

    @Override
    public Collision getCollision() {
        return new SpinyCollision(this);
    }
}
