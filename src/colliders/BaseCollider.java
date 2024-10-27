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

    @Override
    public void setPosition(int x, int y) {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        Rectangle previousBounds = new Rectangle(bounds);
        bounds.setLocation(x, y);
        collisionsEngine.updateColliderBounds(previousBounds, this);
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    @Override
    public Vector2D getVelocity() {
        return velocity.clone();
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void activate() {
        activated = true;
        CollisionsEngine.instance().add(this);
    }

    @Override
    public void deactivate() {
        activated = false;
        CollisionsEngine.instance().remove(this);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
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

    @Override
    public void moveX() {
        bounds.translate((int) velocity.getXComponent(), 0);
    }

    @Override
    public void moveY() {
        bounds.translate(0, (int) velocity.getYComponent());
    }

    @Override
    public void resetVelocity() {
        velocity = nextVelocity;
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    @Override
    public Dimension getSize() {
        return bounds.getSize();
    }

    @Override
    public void setSize(int width, int height) {
        bounds.setSize(width, height);
        CollisionsEngine.instance().updateColliderBounds(bounds, this);
    }

    @Override
    public int displaceX(Rectangle intersection, int coefficient) {
        int signX = (int) -Math.signum(velocity.getXComponent());
        int displacement = signX * (int) (intersection.getWidth() + coefficient);
        translate(displacement, 0);

        return displacement;
    }

    @Override
    public int displaceY(Rectangle intersection, int coefficient) {
        int signY = (int) -Math.signum(velocity.getYComponent());
        int displacement = signY * (int) (intersection.getHeight() + coefficient);
        translate(0, displacement);

        return displacement;
    }

    public void handleHorizontalCollision(Collision c) {
        c.setManaged(false);
    }

    public void handleVerticalCollision(Collision c) {
        c.setManaged(false);
    }
}
