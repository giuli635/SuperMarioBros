package colliders;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SuperMushroomCollision;
import entities.Entity;
import entities.SuperMushroom;
import game.CollisionsEngine;
import game.GraphicEngine;

public class SuperMushroomCollider extends BaseCollider {
    protected Entity m;

    public  SuperMushroomCollider(Entity mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public Entity getEntity() {
        return m;
    }

    public SuperMushroom getSuperMushroom() {
        return (SuperMushroom) m;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new SuperMushroomCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        m.getCollider().getEntity().getGraphicElement().setSprite(new ImageIcon("sprites/superMarioStill.png"));
        CollisionsEngine.instance().remove(this);
        GraphicEngine.instance().removeGraphicElement(this.getEntity().getGraphicElement());
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().getGraphicElement().setSprite(new ImageIcon("sprites/superMarioStill.png"));
        CollisionsEngine.instance().remove(this);
        GraphicEngine.instance().removeGraphicElement(this.getEntity().getGraphicElement());
    }
    
}
