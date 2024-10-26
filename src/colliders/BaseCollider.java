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
    protected boolean colliding;
    protected boolean moving;
    protected Vector2D nextVelocity;

    @Override
    public boolean isMoving() {
        return moving;
    }

    @Override
    public void setMoving(boolean m) {
        moving = m;
    }

    @Override
    public boolean isColliding() {
        return colliding;
    }

    @Override
    public void setColliding(boolean c) {
        colliding = c;
    }

    public BaseCollider(Rectangle b) {
        colliding = false;
        moving = false;
        activated = false;
        bounds = b;
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    @Override
    public Point getPosition() {
        return bounds.getLocation();
    }

    public void setPosition(int x, int y) {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        Rectangle previousBounds = new Rectangle(bounds);
        bounds.setLocation(x, y);
        collisionsEngine.updateColliderBounds(previousBounds, this);
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    public Vector2D getVelocity() {
        return velocity.clone();
    }

    public boolean isActivated() {
        return activated;
    }

    public void activate() {
        activated = true;
        CollisionsEngine.instance().add(this);
    }

    public void deactivate() {
        activated = false;
        CollisionsEngine.instance().remove(this);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void translate(int dx, int dy) {
        if (colliding) {
            CollisionsEngine collisionsEngine = CollisionsEngine.instance();
            Rectangle previousBounds = new Rectangle(bounds);
            bounds.translate(dx, dy);
            collisionsEngine.updateColliderBounds(previousBounds, this);
        } else if (moving) {
            nextVelocity.grow(dx, dy);
            CollisionsEngine.instance().addToUpdate(this);
        } else {
            velocity.grow(dx, dy);
            CollisionsEngine.instance().addToUpdate(this);
        }
    }

    public void moveX() {
        bounds.translate((int) velocity.getXComponent(), 0);
    }

    public void moveY() {
        bounds.translate(0, (int) velocity.getYComponent());
    }

    public void resetVelocity() {
        velocity = nextVelocity;
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    public Dimension getSize() {
        return bounds.getSize();
    }

    public void setSize(int width, int height) {
        bounds.setSize(width, height);
        CollisionsEngine.instance().updateColliderBounds(bounds, this);
    }

    public void handleHorizontalCollision(Collision c) {
    }

    public void handleVerticalCollision(Collision c) {
    }
}
