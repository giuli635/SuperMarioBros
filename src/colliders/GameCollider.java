package colliders;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import collisions.GameCollision;
import collisions.GoombaCollision;
import collisions.MarioCollision;
import entities.Entity;
import entities.Mario;


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
        Mario mario = (Mario) m.getCollider().getEntity();
        Vector2D colliderVelocity = m.getCollider().getVelocity();
        m.getCollider().translate(0, (int) -colliderVelocity.getYComponent());
        m.getCollider().getEntity().getGraphicElement().translate(0, (int) -colliderVelocity.getYComponent());
        if(mario.isFalling()) {
            mario.land();   
        }
    }
}
