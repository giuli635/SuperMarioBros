package colliders;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import game.CollisionsEngine;
import utils.Vector2D;

public abstract class BaseCollider implements Collider {
    private Rectangle bounds;
    private boolean activated;
    private Vector2D velocity;
    private boolean colliding;
    private boolean moving;
    private Vector2D nextVelocity;
    private Collider baseCollider;

    public BaseCollider(Collider c) {
        colliding = false;
        moving = false;
        activated = false;
        baseCollider = c;
        bounds = c.getBounds();
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    public BaseCollider(Rectangle b) {
        colliding = false;
        moving = false;
        activated = false;
        bounds = b;
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        baseCollider = null;
    }

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

    public void setActivated(boolean status) {
        activated = status;
    }

    @Override
    public void activate() {
        CollisionsEngine.instance().add(this);
    }

    @Override
    public void deactivate() {
        CollisionsEngine.instance().remove(this);
        velocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
        nextVelocity = new Vector2D(bounds.getLocation(), bounds.getLocation());
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void translate(int dx, int dy) {
        if (!activated) {
            bounds.translate(dx, dy);
        } else if (colliding) {
            CollisionsEngine collisionsEngine = CollisionsEngine.instance();
            Rectangle previousBounds = new Rectangle(bounds);
            bounds.translate(dx, dy);
            nextVelocity.translate(dx, dy);
            collisionsEngine.updateColliderBounds(previousBounds, this);
        } else if (moving) {
            nextVelocity.grow(dx, dy);
            CollisionsEngine.instance().addToUpdate(this);
        } else {
            velocity.grow(dx, dy);
            nextVelocity.translate(dx, dy);
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
    public void updateVelocity() {
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

    public Collider getBaseCollider() {
        return baseCollider;
    }

    public void setBaseCollider(Collider c) {
        Rectangle previousBounds = bounds;

        baseCollider = c;
        if (c != null) {
            bounds = baseCollider.getBounds();
        } else {
            bounds = new Rectangle(bounds);
        }

        CollisionsEngine.instance().updateColliderBounds(previousBounds, this);
    }
}
