package colliders;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import collisions.GameCollision;
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
    public void sendCollision(Collision c, Direction d) {
    }

    @Override
    public Collision getCollision() {
        return new GameCollision();
    }
}
