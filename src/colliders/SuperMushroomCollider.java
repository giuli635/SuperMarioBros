package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.SuperMushroomCollision;
import entities.SuperMushroom;
import entities.UpdatableEntity;
import entities.mario.Mario;
import entities.mario.SuperMario;
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

    protected void handleMarioCollision(Mario m) {
        new SuperMario().setFunctionality(m);
        getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
        CollisionsEngine.instance().remove(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        handleMarioCollision(m.getCollider().getEntity());
    }

    public void handleHorizontalCollision(MarioCollision m) {
        handleMarioCollision(m.getCollider().getEntity());
    }
}
