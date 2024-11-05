package colliders.updateables.powerups;

import collisions.updateables.mario.FireMarioCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.powerups.PowerUp;
import game.SoundManager;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.MovableEntityCollider;

public abstract class PowerUpCollider extends BaseCollider implements MovableEntityCollider {
    public abstract PowerUp getEntity();

    public PowerUpCollider(Rectangle b) {
        super(b);
    }

    protected void remove() {
        SoundManager.instance().playSound(PowerUp.SOUND);
        deactivate();
        getEntity().unload();
        getEntity().getGraphicElement().remove();
    }

    public void handleCollision(MarioCollision m) {
        remove();
    }

    public void handleCollision(SuperMarioCollision m) {
        remove();
    }

    public void handleCollision(FireMarioCollision m) {
        remove();
    }

    public void handleVerticalCollision(MarioCollision m) {
        handleCollision(m);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        handleCollision(m);
    }

    public void handleVerticalCollision(SuperMarioCollision m) {
        handleCollision(m);
    }

    public void handleHorizontalCollision(SuperMarioCollision m) {
        handleCollision(m);
    }

    public void handleHorizontalCollision(FireMarioCollision m) {
        handleCollision(m);
    }

    public void handleVerticalCollision(FireMarioCollision m) {
        handleCollision(m);
    }
}
