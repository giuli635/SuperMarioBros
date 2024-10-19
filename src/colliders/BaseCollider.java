package colliders;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import game.CollisionsEngine;

public abstract class BaseCollider implements Collider {
    protected Rectangle bounds;
    protected boolean activated;
    protected Vector2D velocity;
    protected boolean moving;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public BaseCollider(Rectangle b) {
        moving = false;
        bounds = b;
        activated = true;
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        CollisionsEngine.instance().add(this);
    }

    public Point getPosition() {
        return bounds.getLocation();
    }

    public void setPosition(int x, int y) {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        collisionsEngine.remove(this);
        bounds.setLocation(x, y);
        collisionsEngine.add(this);
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    public Vector2D getVelocity() {
        return velocity.clone();
    }

    public boolean activated() {
        return activated;
    }

    public void setActive(boolean a) {
        activated = a;
    }

    public Rectangle getBound() {
        return bounds;
    }

    public void translate(int dx, int dy) {
        if (moving) {
            CollisionsEngine collisionsEngine = CollisionsEngine.instance();
            collisionsEngine.remove(this);
            bounds.translate(dx, dy);
            collisionsEngine.add(this);
        } else {
            velocity.grow(dx, dy);
            CollisionsEngine.instance().addToUpdate(this);
        }
    }

    public void moveX() {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        collisionsEngine.remove(this);
        bounds.translate((int) velocity.getXComponent(), 0);
        collisionsEngine.add(this);
    }

    public void moveY() {
        bounds.translate(0, (int) velocity.getYComponent());
    }

    public void resetVelocity() {
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    public Dimension getSize() {
        return bounds.getSize();
    }

    public void setSize(int width, int height) {
        bounds.setSize(width, height);
    }

    public void handleHorizontalCollision(Collision c) {
    }

    public void handleVerticalCollision(Collision c) {
    }
}
