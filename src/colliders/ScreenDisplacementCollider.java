package colliders;

import java.awt.Rectangle;

import collisions.Collision;
import collisions.MarioCollision;
import collisions.ScreenDisplacementCollision;
import entities.Entity;
import game.GraphicEngine;

public class ScreenDisplacementCollider extends BaseCollider {
    public ScreenDisplacementCollider(Rectangle b) {
        super(b);
    }

    public Entity getEntity() {
        return null;
    }

    @Override
    public Collision getCollision() {
        return new ScreenDisplacementCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Direction d) {
        c.collide(this, d);
    }

    public void handleCollision(MarioCollision m, Direction d) {
        translate((int) m.getCollider().getVelocity().getXComponent(), 0);
        GraphicEngine.instance().scrollScreen((int) m.getCollider().getVelocity().getXComponent());
    }
}
