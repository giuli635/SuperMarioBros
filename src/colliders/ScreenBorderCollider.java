package colliders;

import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import collisions.GameCollision;
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
    public void sendCollision(Collision c, Direction d) {
        c.collide(this, d);
    }

    @Override
    public Collision getCollision() {
        return new ScreenBorderCollision(this);
    }

    public void handleCollision(MarioCollision m, Direction d) {
        MarioCollider collider = m.getCollider();
        int velocity = (int) collider.getVelocity().getXComponent();
        collider.translate(-velocity, 0);
        collider.getEntity().getGraphicElement().translate(-Math.abs(velocity), 0);
    }

    public void handleCollision(GameCollision g, Direction d) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GraphicElement graphicElement = g.getCollider().getEntity().getGraphicElement();
        if (d != position) {
            graphicEngine.removeGraphicElement(graphicElement);
        } else {
            Point colliderPosition = g.getCollider().getPosition();
            if (position == Direction.RIGTH) {
                graphicElement.setPosition(
                    (int) (colliderPosition.getX() - getBound().getMaxX() + graphicEngine.getPanelSize().getWidth()),
                    (int) (graphicEngine.getPanelSize().getHeight() - colliderPosition.getY())
                );
            } else {
                graphicElement.setPosition(
                    (int) (colliderPosition.getX() - getPosition().getX()),
                    (int) (graphicEngine.getPanelSize().getHeight() - getPosition().getY())
                );
            }
            graphicEngine.addGraphicElement(graphicElement);
        }
    }
}
