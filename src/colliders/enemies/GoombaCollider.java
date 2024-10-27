package colliders.enemies;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.GoombaCollision;
import entities.enemies.Goomba;

public class GoombaCollider extends EnemyCollider {
    protected Goomba goomba;

    public GoombaCollider(Goomba g, Rectangle b) {
        super(b);
        goomba = g;
    }

    @Override
    public Goomba getEntity() {
        return goomba;
    }

    @Override
    public GoombaCollision getCollision() {
        return new GoombaCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}
