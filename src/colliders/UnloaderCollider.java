package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.UnloaderCollision;
import entities.Entity;
import game.GraphicEngine;
import graphics.GraphicElement;

public class UnloaderCollider extends BaseCollider{

    public UnloaderCollider(Rectangle b) {
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
        return new UnloaderCollision(this);
    }

    public void handleHorizontalCollision(UnloaderCollision c) {
    }

    public void handleHorizontalCollision(Collision c) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();
        graphicEngine.removeGraphicElement(graphicElement);
    }
}
