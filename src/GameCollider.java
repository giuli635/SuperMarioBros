import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class GameCollider implements Collider {
    protected Entity entity;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public GameCollider(Entity e, Rectangle b) {
        entity = e;
        bounds = b;
        activated = true;
        previousPosition = b.getLocation();
    }

    public GameCollider(Rectangle b) {//x si queremos un collider sin una entidad asociada, quien sabe..//
        bounds = b;
        activated = true;
        previousPosition = b.getLocation();
    }

    public Entity getEntity() {
        return entity;
    }

    public Point getPosition() {
        return bounds.getLocation();
    }

    public void setPosition(int x, int y) {
        previousPosition = bounds.getLocation();
        bounds.setLocation(x, y);
    }

    public Vector2D getVelocity() {
        return new Vector2D(bounds.getLocation(), previousPosition);
    }

    public boolean activated() {
        return activated;
    }

    public void setActive(boolean a) {
        activated = a;
    }

    @Override
    public void handleCollision(Collision c, Direction d) {
    }

    @Override
    public Rectangle getBound() {
        return bounds;
    }

    @Override
    public Collision getCollision() {
        return new GameCollision();
    }

    @Override
    public void translate(int dx, int dy) {
        previousPosition = bounds.getLocation();
        bounds.translate(dx, dy);
    }

    @Override
    public Dimension getSize() {
        return bounds.getSize();
    }

    @Override
    public void setSize(int width, int height) {
        bounds.setSize(width, height);
    }
}
