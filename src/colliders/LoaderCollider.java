package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.LoaderCollision;
import collisions.UpdateableEntityCollision;
import entities.Entity;

public class LoaderCollider extends BaseCollider{

    public LoaderCollider(Rectangle b) {
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
    public Collision getCollision() {
        return new LoaderCollision(this);
    }
    
    public void handleHorizontalCollision(UpdateableEntityCollision c) {
        c.getCollider().getEntity().load();
    }
}
