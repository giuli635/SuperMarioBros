package colliders;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import entities.Entity;
import utils.Axis;
import utils.Vector2D;

public interface Collider {
    public Entity getEntity();
    public void recieveCollision(Collision c, Axis a);
    public Rectangle getBounds();
    public Collision getCollision();
    public void setPosition(int x, int y);
    public Point getPosition();
    public void translate(int dx, int dy);
    public Dimension getSize();
    public void setSize(int width, int height);
    public Vector2D getVelocity();
    public Vector2D getNextVelocity();
    public boolean isActivated();
    public void setActivated(boolean status);
    public void activate();
    public void deactivate();
    public void updateVelocity();
    public void moveX();
    public void moveY();
    public void setColliding(boolean c);
    public boolean isColliding();
    public boolean isMoving();
    public void setMoving(boolean m);
    public int displaceX(Rectangle intersection, int coefficient);
    public int displaceY(Rectangle intersection, int coefficient);
    public void copy(Collider c);
    public void track(Collider c);
}
