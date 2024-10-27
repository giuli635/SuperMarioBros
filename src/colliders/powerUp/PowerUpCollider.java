package colliders.powerUp;

import colliders.UpdateableEntityCollider;
import collisions.MarioCollision;
import entities.mario.Mario;
import entities.powerUp.PowerUp;
import game.CollisionsEngine;
import game.GraphicEngine;

import java.awt.Rectangle;

import colliders.BaseCollider;

public abstract class PowerUpCollider extends BaseCollider implements UpdateableEntityCollider {
    public abstract PowerUp getEntity();

    public PowerUpCollider(Rectangle b) {
        super(b);
    }

    protected void handleMarioCollision(Mario m) {
        getEntity().getState().setFunctionality(m);
        getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
        CollisionsEngine.instance().remove(this);
        m.getCollider().getEntity().addPoints(getEntity().getPoints());
    }

    public void handleVerticalCollision(MarioCollision m) {
        handleMarioCollision(m.getCollider().getEntity());
    }

    public void handleHorizontalCollision(MarioCollision m) {
        handleMarioCollision(m.getCollider().getEntity());
    }

}
