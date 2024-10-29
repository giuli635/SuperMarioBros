package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.Collision;
import collisions.invisibles.UnloaderCollision;
import entities.Entity;
import game.GraphicEngine;
import graphics.GameGraphicElement;
import utils.Axis;

public class GraphicUnloaderCollider extends BaseCollider {

    public GraphicUnloaderCollider(Rectangle b) {
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
    public UnloaderCollision getCollision() {
        return new UnloaderCollision(this);
    }

    public void handleHorizontalCollision(Collision c) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GameGraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();
        graphicEngine.remove(graphicElement);
    }
}
