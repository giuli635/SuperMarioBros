package colliders.updateables.mario;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.UpdateableEntityCollider;
import collisions.updateables.mario.MarioCollision;
import entities.updateables.mario.Mario;
import game.CollisionsEngine;

public abstract class MarioCollider extends BaseCollider implements UpdateableEntityCollider {
    protected Mario mario;
    protected MarioCollider baseCollider;

    public MarioCollider(MarioCollider c) {
        super(c.getBounds());
        baseCollider = c;
    }

    public MarioCollider(Rectangle b) {
        super(b);
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    public abstract MarioCollision getCollision();

    public MarioCollider getBaseCollider() {
        return baseCollider;
    }

    public void setBaseCollider(MarioCollider c) {
        Rectangle previousBounds = bounds;

        baseCollider = c;
        if (c != null) {
            bounds = baseCollider.getBounds();
        } else {
            bounds = new Rectangle(bounds);
        }

        CollisionsEngine.instance().updateColliderBounds(previousBounds, this);
    }
}

