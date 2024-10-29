package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.updateables.enemies.PiranhaPlantCollision;
import entities.updateables.enemies.PiranhaPlant;
import utils.Axis;

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
