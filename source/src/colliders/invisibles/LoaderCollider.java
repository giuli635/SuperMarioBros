package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.VisitorCollision;
import collisions.invisibles.LoaderCollision;
import collisions.updateables.UpdateableEntityCollision;
import entities.Entity;
import utils.Axis;

public class LoaderCollider extends BaseCollider {
    public LoaderCollider(Rectangle b) {
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
    public LoaderCollision getCollision() {
        return new LoaderCollision(this);
    }
    
    public void handleHorizontalCollision(UpdateableEntityCollision c) {
        if (!c.getCollider().getEntity().loaded()) {
            c.getCollider().getEntity().load();
            c.getCollider().activate();
        }
    }
}
