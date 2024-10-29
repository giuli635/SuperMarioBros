package colliders.updateables.powerups;

import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.powerups.PowerUp;
import game.GraphicEngine;
import game.SoundManager;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.UpdateableEntityCollider;
import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.SuperMarioCollider;

public abstract class PowerUpCollider extends BaseCollider implements UpdateableEntityCollider {
    public abstract PowerUp getEntity();

    public PowerUpCollider(Rectangle b) {
        super(b);
    }

    protected void remove() {
        SoundManager.instance().playSound(PowerUp.SOUND);
        deactivate();
        getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
    }

    public void handleCollision(MarioCollider m) {
        remove();
    }

    public void handleCollision(SuperMarioCollider m) {
        remove();
    }

    public void handleVerticalCollision(MarioCollision m) {
        handleCollision(m.getCollider());
    }

    public void handleHorizontalCollision(MarioCollision m) {
        handleCollision(m.getCollider());
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        handleCollision(m.getCollider());
    }

    public void handleHorizontalCollision(SuperMarioCollision m) {
        handleCollision(m.getCollider());
    }
}
