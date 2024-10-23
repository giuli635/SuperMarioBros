package colliders;

import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.ScreenBorderCollision;
import entities.Entity;
import game.GraphicEngine;
import graphics.GraphicElement;

public class ScreenBorderCollider extends BaseCollider {
    protected Direction position;

    public ScreenBorderCollider(Rectangle b, Direction position) {
        super(b);
        this.position = position;
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
        return new ScreenBorderCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBound().intersection(m.getCollider().getBound());
        m.getCollider().translate((int) collision.getWidth(), 0);
        m.getCollider().getEntity().getGraphicElement().translate((int) collision.getWidth(), 0);
    }

    public void handleHorizontalCollision(ScreenBorderCollision s){
    }

    public void handleHorizontalCollision(Collision g) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GraphicElement graphicElement = g.getCollider().getEntity().getGraphicElement();
        Direction collisionDirection = getVelocity().getXComponent() > 0 ? Direction.RIGHT : Direction.LEFT;
        if  (collisionDirection == position) {
            Point colliderPosition = g.getCollider().getPosition();
            graphicElement.setPosition(
                (int) (colliderPosition.getX() - graphicEngine.getPosition()),
                (int) colliderPosition.getY()
            );
            graphicEngine.addGraphicElement(graphicElement);
        }
    }
}
