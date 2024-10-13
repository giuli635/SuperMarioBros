import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public interface Collider {
    public Entity getEntity();
    public void handleCollision(Collision c, Direction d);
    public Rectangle getBound();
    public Collision getCollision();
    public void setPosition(int x, int y);
    public Point getPosition();
    public void translate(int dx, int dy);
    public Dimension getSize();
    public void setSize(int width, int height);
    public Vector2D getVelocity();
    public void setActive(boolean b);
    public boolean activated();
}
