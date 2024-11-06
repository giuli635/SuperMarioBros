package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.VisitorCollision;
import collisions.invisibles.DeleterCollision;
import collisions.updateables.UpdateableEntityCollision;
import entities.Entity;
import utils.Axis;

public class DeleterCollider extends BaseCollider {
    public DeleterCollider(Rectangle b) {
        super(b);
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public DeleterCollision getCollision() {
        return new DeleterCollision(this);
    }

    public void delete(VisitorCollision c) {
        c.getCollider().deactivate();
    }

    public void handleHorizontalCollision(UpdateableEntityCollision c) {
        delete(c);
        c.getCollider().getEntity().unload();
    }

    public void handleHorizontalCollision(VisitorCollision c) {
        delete(c);
    }
}
