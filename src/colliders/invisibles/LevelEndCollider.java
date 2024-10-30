package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.Collision;
import collisions.invisibles.LevelEndCollision;
import collisions.invisibles.ScreenDisplacementCollision;
import entities.Entity;
import utils.Axis;

public class LevelEndCollider extends BaseCollider{

    public LevelEndCollider(Rectangle b) {
        super(b);
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public LevelEndCollision getCollision() {
        return new LevelEndCollision(this);
    }

    public void handleHorizontalCollision(ScreenDisplacementCollision c) {
        c.getCollider().deactivate();
    }
}
