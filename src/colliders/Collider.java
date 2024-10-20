package colliders;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import collisions.Axis;
import entities.Entity;

public interface Collider {
    public Entity getEntity();
    public void sendCollision(Collision c, Axis a);
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
    public void resetVelocity();
    public void moveX();
    public void moveY();
    public void setColliding(boolean c);
    public boolean isColliding();
    public boolean isMoving();
    public void setMoving(boolean m);
}
