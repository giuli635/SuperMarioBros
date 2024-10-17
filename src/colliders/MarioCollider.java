package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.MarioCollision;
import entities.Entity;
import entities.Mario;

public class MarioCollider extends BaseCollider {
    protected Mario mario;

    public MarioCollider(Mario m, Rectangle b) {
        super(b);
        mario = m;
    }

    @Override
    public Entity getEntity() {
        return mario;
    }

    @Override
    public Collision getCollision() {
        return new MarioCollision(this);
    }

    @Override
    public void sendCollision(Collision c) {
        c.collide(this);
    }
}

