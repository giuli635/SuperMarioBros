package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.MarioCollision;
import collisions.ScreenDisplacementCollision;
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
    public void sendCollision(Collision c, Direction d) {
        c.collide(this, d);
    }

    public void handleCollision(ScreenDisplacementCollision c, Direction d) {
        mario.getGraphicElement().translate((int) -getVelocity().getXComponent(), 0);
    }
}

