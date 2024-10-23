package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SuperMushroomCollision;
import entities.SuperMushroom;
import entities.UpdatableEntity;
import game.CollisionsEngine;
import game.GraphicEngine;

public class SuperMushroomCollider extends BaseCollider implements UpdateableEntityCollider {
    protected SuperMushroom m;

    public  SuperMushroomCollider(SuperMushroom mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public UpdatableEntity getEntity() {
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
        //m.getCollider().getEntity().getGraphicElement().setSprite(new ImageIcon("sprites/superMarioStill.png"));
        getEntity().unload();
        GraphicEngine.instance().removeGraphicElement(getEntity().getGraphicElement());
        CollisionsEngine.instance().remove(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        //m.getCollider().getEntity().getGraphicElement().setSprite(new ImageIcon("sprites/superMarioStill.png"));
        getEntity().unload();
        GraphicEngine.instance().removeGraphicElement(getEntity().getGraphicElement());
        CollisionsEngine.instance().remove(this);
    }
    
}
