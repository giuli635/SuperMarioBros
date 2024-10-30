package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.Collision;
import collisions.invisibles.ScreenDisplacementCollision;
import collisions.updateables.mario.MarioCollision;
import entities.Entity;
import game.CollisionsEngine;
import game.GraphicEngine;
import utils.Axis;

public class ScreenDisplacementCollider extends BaseCollider {
    protected ScreenBorderCollider leftBorder;
    protected ScreenBorderCollider rightBorder;
    protected LoaderCollider loader;
    protected GraphicUnloaderCollider unloader;
    protected DeleterCollider deleter;

    public ScreenDisplacementCollider(
            Rectangle b, ScreenBorderCollider left, ScreenBorderCollider right,
            LoaderCollider l, GraphicUnloaderCollider ul, DeleterCollider d
    ) {
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
    public ScreenDisplacementCollision getCollision() {
        return new ScreenDisplacementCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        translate((int) collision.getWidth(), 0);
        CollisionsEngine.instance().addToUpdate(this);
        leftBorder.translate((int) collision.getWidth(), 0);
        rightBorder.translate((int) collision.getWidth(), 0);
        loader.translate((int) collision.getWidth(), 0);
        unloader.translate((int) collision.getWidth(), 0);
        deleter.translate((int) collision.getWidth(), 0);
        GraphicEngine.instance().scrollScreen((int) -collision.getWidth());
    }
}
