package colliders.enemies;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.PiranhaPlantCollision;
import entities.enemies.PiranhaPlant;

public class PiranhaPlantCollider extends EnemyCollider {
    protected PiranhaPlant piranha;

    public PiranhaPlantCollider(PiranhaPlant p, Rectangle b) {
        super(b);
        piranha = p;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public PiranhaPlantCollision getCollision() {
        return new PiranhaPlantCollision(this);
    }

    @Override
    public PiranhaPlant getEntity() {
        return piranha;
    }
}
