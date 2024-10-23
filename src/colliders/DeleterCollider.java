package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.DeleterCollision;
import collisions.UpdateableEntityCollision;
import entities.Entity;
import game.CollisionsEngine;
import game.GraphicEngine;
import graphics.GraphicElement;

public class DeleterCollider extends BaseCollider{

    public DeleterCollider(Rectangle b) {
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
        return new DeleterCollision(this);
    }

    public void handleHorizontalCollision(DeleterCollision c) {
    }

    public void handleHorizontalCollision(UpdateableEntityCollision c) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();
        c.getCollider().getEntity().unload();
        graphicEngine.removeGraphicElement(graphicElement);
        CollisionsEngine.instance().remove(c.getCollider());
    }

    public void handleHorizontalCollision(Collision c) {
        GraphicEngine graphicEngine = GraphicEngine.instance();
        GraphicElement graphicElement = c.getCollider().getEntity().getGraphicElement();
        graphicEngine.removeGraphicElement(graphicElement);
        CollisionsEngine.instance().remove(c.getCollider());
    }
}
