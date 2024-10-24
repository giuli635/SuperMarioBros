package game;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import colliders.Collider;
import collisions.Axis;

public class CollisionsEngine {
    protected static CollisionsEngine uniqueInstance;
    protected List<List<Collider>> chunks;
    protected Set<Collider> toUpdate;

    public CollisionsEngine() {
        reset();
    }

    public static CollisionsEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollisionsEngine();
        }
        return uniqueInstance;
    }

    protected void checkCollision(Collider c1, Collider c2, Axis axis) {
        if (c1.getBound().intersects(c2.getBound())) {
            c2.sendCollision(c1.getCollision(), axis);
            c1.sendCollision(c2.getCollision(), axis);
        }
    }

    public void update() {
        List<Collider> toUpdateCopy = new ArrayList<>(toUpdate);
        for (Collider collider : toUpdateCopy) {
            collider.setMoving(true);
            removeFromChunks(collider);
            collider.moveX();
            add(collider);
        }

        checkCollisions(toUpdateCopy, Axis.X);

        toUpdateCopy = new ArrayList<>(toUpdate);
        for (Collider collider : toUpdateCopy) {
            collider.moveY();
        }

        checkCollisions(toUpdateCopy, Axis.Y);

        for (Collider collider : toUpdateCopy) {
            collider.setMoving(false);
            collider.resetVelocity();
        }
    }

    public void checkCollisions(Iterable<Collider> collidersToCheck, Axis axis) {
        for (Collider collider : collidersToCheck) {
            int[] chunkRange = calculateChunk(collider);
            for (int i = chunkRange[0]; i <= chunkRange[1] && i < chunks.size(); i++) {
                List<Collider> chunk = new ArrayList<>(chunks.get(i));
                for (Collider toCheck : chunk) {
                    collider.setColliding(true);
                    toCheck.setColliding(true);
                    checkCollision(collider, toCheck, axis);
                    toCheck.setColliding(false);
                }
            }
            collider.setColliding(false);
        }
    }

    public void translateCollider(Collider c, int dx, int dy) {
        removeFromChunks(c);
        c.getBound().translate(dx, dy);
        add(c);
    }

    public void setColliderPosition(Collider c, int x, int y) {
        removeFromChunks(c);
        c.getBound().setLocation(x, y);
        add(c);
    }

    protected int[] calculateChunk(int minX, int maxX) {
        int firstChunk = (int) Math.floor(minX / 32.0);
        int secondChunk = (int) Math.ceil(maxX / 32.0);
        return new int[]{firstChunk, secondChunk};
    }

    protected int[] calculateChunk(Collider collider) {
        Rectangle bounds = collider.getBound();
        return calculateChunk((int) bounds.getX(), (int) bounds.getMaxX());
    }

    public void add(Collider collider) {
        int[] chunkRange = calculateChunk(collider);
        if (chunkRange[0] >= chunks.size()) {
            for (int i = 0; i < chunkRange[0]; i++) {
                chunks.add(new Vector<>());
            }
        }

        for(int i = chunkRange[0]; i < chunks.size() && i < chunkRange[1]; i++) {
            chunks.get(i).add(collider);
        }

        for(int i = chunks.size(); i < chunkRange[1]; i++) {
            chunks.add(new Vector<>());
            chunks.get(i).add(collider);
        }
    }

    protected void removeFromChunks(Collider c) {
        int[] chunkRange = calculateChunk(c);
        for(int i = chunkRange[0]; i < chunkRange[1] && i < chunks.size(); i++) {
            chunks.get(i).remove(c);
        }
    }

    public void remove(Collider c) {
        removeFromChunks(c);
        toUpdate.remove(c);
    }

    public Iterable<Collider> getCollidersInRange(int lowerBound, int higherBound) {
        Collection<Collider> collidersInRange = new ArrayList<>();
        int[] range = calculateChunk(lowerBound, higherBound);
        for (int i = range[0]; i < range[1]; i++) {
            collidersInRange.addAll(chunks.get(i));
        }

        return collidersInRange;
    }

    public void addToUpdate(Collider c) {
        toUpdate.add(c);
    }

    public void reset(){
        chunks = new Vector<List<Collider>>();
        toUpdate = new HashSet<>();
    }
}
