package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.ScreenDisplacementCollision;
import entities.Entity;
import game.GraphicEngine;

public class ScreenDisplacementCollider extends BaseCollider {
    protected ScreenBorderCollider leftBorder;
    protected ScreenBorderCollider rightBorder;
    protected LoaderCollider loader;
    protected UnloaderCollider unloader;
    protected DeleterCollider deleter;

    public ScreenDisplacementCollider(Rectangle b, ScreenBorderCollider left, ScreenBorderCollider right, LoaderCollider l, UnloaderCollider ul, DeleterCollider d) {
        super(b);
        leftBorder = left;
        rightBorder = right;
        loader = l;
        unloader = ul;
        deleter = d;
    }

    public Entity getEntity() {
        return null;
    }

    @Override
    public Collision getCollision() {
        return new ScreenDisplacementCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        translate((int) collision.getWidth(), 0);
        leftBorder.translate((int) collision.getWidth(), 0);
        rightBorder.translate((int) collision.getWidth(), 0);
        loader.translate((int) collision.getWidth(), 0);
        unloader.translate((int) collision.getWidth(), 0);
        deleter.translate((int) collision.getWidth(), 0);
        GraphicEngine.instance().scrollScreen((int) -collision.getWidth());
    }
}
