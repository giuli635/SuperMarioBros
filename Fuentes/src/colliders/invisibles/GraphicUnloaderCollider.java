package colliders.invisibles;

import java.awt.Rectangle;

import colliders.BaseCollider;
import collisions.VisitorCollision;
import collisions.invisibles.UnloaderCollision;
import entities.Entity;
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
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public UnloaderCollision getCollision() {
        return new UnloaderCollision(this);
    }

    public void handleHorizontalCollision(VisitorCollision c) {
        GameGraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();
        if (graphicElement != null) {
            graphicElement.remove();
        }
    }
}
