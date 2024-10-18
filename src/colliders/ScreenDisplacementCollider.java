package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.MarioCollision;
import collisions.ScreenDisplacementCollision;
import entities.Entity;
import game.CollisionsEngine;
import game.GraphicEngine;

public class ScreenDisplacementCollider extends BaseCollider {
    protected ScreenBorderCollider leftBorder;
    protected ScreenBorderCollider rightBorder;

    public ScreenDisplacementCollider(Rectangle b, ScreenBorderCollider left, ScreenBorderCollider right) {
        super(b);
        leftBorder = left;
        rightBorder = right;
    }

    public Entity getEntity() {
        return null;
    }

    @Override
    public Collision getCollision() {
        return new ScreenDisplacementCollision(this);
    }

    @Override
    public void sendCollision(Collision c) {
        c.collide(this);
    }

    public void handleCollision(MarioCollision m) {
        Rectangle collision = getBound().intersection(m.getCollider().getBound());
        translate((int) collision.getWidth(), 0);
        leftBorder.translate((int) collision.getWidth(), 0);
        rightBorder.translate((int) collision.getWidth(), 0);
        GraphicEngine.instance().scrollScreen((int) -collision.getWidth());
        CollisionsEngine.instance().addToCheck(leftBorder);
        CollisionsEngine.instance().addToCheck(rightBorder);
    }
}
