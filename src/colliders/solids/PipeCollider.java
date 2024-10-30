package colliders.solids;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.solids.PipeCollision;
import collisions.updateables.enemies.PiranhaPlantCollision;
import entities.solids.Pipe;
import utils.Axis;

public class PipeCollider extends SolidCollider {
    protected Pipe pipe;

    public PipeCollider(Pipe e, Rectangle b) {
        super(b);
        pipe = e;
    }

    public Pipe getEntity() {
        return pipe;
    }

    @Override
    public void recieveCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public PipeCollision getCollision() {
        return new PipeCollision(this);
    }

    public void handleVerticalCollision(PiranhaPlantCollision p) {
    }

    public void handleHorizontalCollision(PiranhaPlantCollision p) {
    }
}
