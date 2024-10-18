package colliders;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import collisions.GameCollision;
import collisions.MarioCollision;
import entities.Entity;

public class GameCollider extends BaseCollider {
    protected Entity entity;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public GameCollider(Entity e, Rectangle b) {
        super(b);
        entity = e;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public void sendCollision(Collision c) {
        c.collide(this);
    }

    @Override
    public Collision getCollision() {
        return new GameCollision(this);
    }

    public void handleCollision(MarioCollision m) {
        Vector2D velocity = m.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(m.getCollider().getBound());
        if (collision.getHeight() < collision.getWidth()) {
            int sign = (int) -Math.signum(velocity.getYComponent());
            m.getCollider().translate(0, sign * (int) collision.getHeight());
            m.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
        } else {
            int sign = (int) -Math.signum(velocity.getXComponent());
            m.getCollider().translate(sign * (int) collision.getWidth(), 0);
            m.getCollider().getEntity().getGraphicElement().translate(sign * (int) collision.getWidth(), 0);
        }
    }
}
