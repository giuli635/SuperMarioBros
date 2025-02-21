package colliders.updateables.mario;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.UpdateableEntityCollider;
import collisions.updateables.mario.MarioCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.CommandMarioStatus;
import game.SingletonCollisionsEngine;
import utils.Prioritizable;

public abstract class MarioCollider extends BaseCollider implements UpdateableEntityCollider, Prioritizable {
    protected Mario mario;
    protected MarioCollider baseCollider;
    protected MarioCollider colliderOnTop;
    protected CommandMarioStatus associatedState;
    protected int priority;

    public MarioCollider(Rectangle b) {
        super(b);
        colliderOnTop = null;
        associatedState = null;
        baseCollider = null;
    }

    @Override
    public Mario getEntity() {
        return mario;
    }

    public abstract MarioCollision getCollision();

    public MarioCollider getBaseCollider() {
        return baseCollider;
    }

    public CommandMarioStatus getAssociatedState() {
        return associatedState;
    }

    public void setBaseCollider(MarioCollider c) {
        Rectangle previousBounds = bounds;

        baseCollider = c;
        if (c != null) {
            bounds = baseCollider.getBounds();
            c.setColliderOnTop(this);
        } else {
            bounds = new Rectangle(bounds);
        }

        SingletonCollisionsEngine.instance().updateColliderBounds(previousBounds, this);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public MarioCollider getColliderOnTop() {
        return colliderOnTop;
    }

    public void setColliderOnTop(MarioCollider collider) {
        colliderOnTop = collider;
        if (collider != null) {
            track(collider);
        }
    }
}

