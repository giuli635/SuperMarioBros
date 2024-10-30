package game;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import colliders.Collider;
import colliders.invisibles.ScreenDisplacementCollider;
import collisions.Collision;
import utils.Axis;

public class CollisionsEngine {
    protected static CollisionsEngine uniqueInstance;
    protected List<List<Collider>> chunks;
    protected Set<Collider> toUpdate;
    protected int frame;

    protected CollisionsEngine() {
        reset();
    }

    public static CollisionsEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollisionsEngine();
        }
        return uniqueInstance;
    }

    protected void checkCollision(Collider c1, Collider c2, Axis axis) {
        if (c2.isActivated() && c1.getBounds().intersects(c2.getBounds())) {
            Collision first = c1.getCollision();
            c2.sendCollision(first, axis);
            if (!first.wasManaged()) {
                c1.sendCollision(c2.getCollision(), axis);
            }
        }
    }

    public void update() {
        for (Collider collider : toUpdate) {
            collider.setMoving(true);
            removeFromChunks(collider.getBounds(), collider);
            collider.moveX();
            add(collider);
        }

        checkCollisions(Axis.X);

        for (Collider collider : toUpdate) {
            collider.moveY();
        }

        checkCollisions(Axis.Y);

        for (Collider collider : toUpdate) {
            collider.setMoving(false);
            collider.updateVelocity();
        }
    }

    protected void checkCollisions(Axis axis) {
        for (Collider collider : toUpdate) {
            int[] chunkRange = calculateChunk(collider.getBounds());

            collider.setColliding(true);
            for (int i = chunkRange[0]; i <= chunkRange[1] && i < chunks.size(); i++) {
                checkChunk(axis, collider, i);
            }
            collider.setColliding(false);
        }
    }

    protected void checkChunk(Axis axis, Collider collider, int i) {
        List<Collider> chunk = new ArrayList<>(chunks.get(i));
        Collider toCheck;
        Iterator<Collider> chunkIterator = chunk.iterator();
        while (chunkIterator.hasNext() && collider.isActivated()) {
            toCheck = chunkIterator.next();
            if (toCheck != collider){
                toCheck.setColliding(true);
                checkCollision(collider, toCheck, axis);
                toCheck.setColliding(false);
            }

        }
    }

    public void updateColliderBounds(Rectangle previousBounds, Collider c) {
        if (c.isActivated()) {
            removeFromChunks(previousBounds, c);
            add(c);
        }
    }

    protected int[] calculateChunk(int minX, int maxX) {
        int firstChunk = (int) Math.floor(minX / (float) LevelReader.CHUNK);
        int secondChunk = (int) Math.ceil(maxX / (float) LevelReader.CHUNK);
        return new int[]{firstChunk, secondChunk};
    }

    protected int[] calculateChunk(Rectangle bounds) {
        return calculateChunk((int) bounds.getX(), (int) bounds.getMaxX());
    }

    public void add(Collider collider) {
        int[] chunkRange = calculateChunk(collider.getBounds());
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

        collider.setActivated(true);
    }

    protected void removeFromChunks(Rectangle bounds, Collider c) {
        int[] chunkRange = calculateChunk(bounds);
        for(int i = chunkRange[0]; i < chunkRange[1] && i < chunks.size(); i++) {
            chunks.get(i).remove(c);
        }
    }

    public void remove(Collider collider) {
        removeFromChunks(collider.getBounds(), collider);
        toUpdate.remove(collider);
        collider.setActivated(false);
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
        toUpdate = ConcurrentHashMap.newKeySet();
    }
}
