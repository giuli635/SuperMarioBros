package colliders;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Collision;
import game.CollisionsEngine;

public abstract class BaseCollider implements Collider {
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public BaseCollider(Rectangle b) {
        bounds = b;
        activated = true;
        previousPosition = b.getLocation();
        updateChunk();
    }

    protected void updateChunk() {
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        collisionsEngine.removeFromChunk(this);
        int firstChunk = (int) (bounds.getMinX() / 32.0);
        int lastChunk = (int) (bounds.getMaxX() / 32.0);
        for(int i = firstChunk; i <= lastChunk; i++) {
            collisionsEngine.addToChunk(i, this);
        }
    }

    public Point getPosition() {
        return bounds.getLocation();
    }

    public void setPosition(int x, int y) {
        previousPosition = bounds.getLocation();
        bounds.setLocation(x, y);
        updateChunk();
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

    public Rectangle getBound() {
        return bounds;
    }

    public void translate(int dx, int dy) {
        previousPosition = bounds.getLocation();
        bounds.translate(dx, dy);
        updateChunk();
    }

    public Dimension getSize() {
        return bounds.getSize();
    }

    public void setSize(int width, int height) {
        bounds.setSize(width, height);
    }

    public void handleCollision(Collision c, Direction d) {
    }
}
