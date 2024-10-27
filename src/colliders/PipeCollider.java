package colliders;

import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.PipeCollision;
import collisions.PiranhaPlantCollision;
import entities.Pipe;

public class PipeCollider extends SolidCollider {
    protected Pipe pipe;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public PipeCollider(Pipe e, Rectangle b) {
        super(b);
        pipe = e;
    }

    public Pipe getEntity() {
        return pipe;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
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
