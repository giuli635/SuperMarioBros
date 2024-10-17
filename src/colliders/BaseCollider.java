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

    public BaseCollider(Rectangle b) {
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
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        collisionsEngine.remove(this);
        velocity.grow(dx, dy);
        bounds.translate(dx, dy);
        collisionsEngine.add(this);
        collisionsEngine.addToReset(this);
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

    public void handleCollision(Collision c) {
    }
}
