import java.awt.Shape;

public abstract class GameCollider implements Collider {
    protected Entity entity;
    protected Shape bounds;
    protected boolean activated;

    public Entity getEntity() {
        return entity;
    }

    public void collider(GameCollider c, Direction d) {
        c.handleCollision(this, d);
    }

    public void handleCollision(Collider c, Direction d) {

    }
}
