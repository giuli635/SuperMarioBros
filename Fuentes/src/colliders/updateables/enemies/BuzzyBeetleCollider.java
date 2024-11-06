package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.enemies.BuzzyBeetleCollision;
import entities.updateables.enemies.BuzzyBeetle;
import utils.Axis;

public class BuzzyBeetleCollider extends ShellEnemyCollider {
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
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }
}
