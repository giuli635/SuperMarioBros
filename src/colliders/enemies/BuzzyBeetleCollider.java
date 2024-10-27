package colliders.enemies;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import entities.enemies.BuzzyBeetle;
import collisions.BuzzyBeetleCollision;

public class BuzzyBeetleCollider extends EnemyCollider {
    protected BuzzyBeetle buzzyBeetle;

    public BuzzyBeetleCollider(BuzzyBeetle z, Rectangle b) {
        super(b);
        buzzyBeetle = z;
    }

    @Override
    public BuzzyBeetle getEntity() {
        return buzzyBeetle;
    }

    @Override
    public BuzzyBeetleCollision getCollision() {
        return new BuzzyBeetleCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}
