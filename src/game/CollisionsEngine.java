package game;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import colliders.Collider;
import colliders.Direction;
import colliders.Vector2D;

public class CollisionsEngine {
    protected static CollisionsEngine uniqueInstance;
    protected Collection<Collider> nextCollidersToCheck;
    protected List<List<Collider>> chunks;

    public CollisionsEngine() {
        nextCollidersToCheck = new ArrayList<>();
        chunks = new Vector<List<Collider>>();
    }

    public static CollisionsEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollisionsEngine();
        }
        return uniqueInstance;
    }

    protected void checkCollision(Collider c1, Collider c2) {
        if (c1.getBound().intersects(c2.getBound())) {
            Vector2D collisionVector = c1.getVelocity().sum( c2.getVelocity());
            Direction dir;

            if (collisionVector.getYComponent() > collisionVector.getXComponent()) {
                dir = Direction.DOWN;
                if (collisionVector.getYComponent() > 0) {
                    dir = Direction.UP;
                }
            } else {
                dir = Direction.RIGTH;
                if (collisionVector.getXComponent() > 0) {
                    dir = Direction.LEFT;
                }
            }
            
            c2.sendCollision(c1.getCollision(), dir);
            c1.sendCollision(c2.getCollision(), Direction.opposite(dir));
        }
    }

    public void checkCollisions() {
        List<Collider> nextCollidersToCheckCopy = new ArrayList<>(nextCollidersToCheck);
        nextCollidersToCheck.removeAll(nextCollidersToCheck);
        for (Collider collider : nextCollidersToCheckCopy) {
            int[] chunkRange = calculateChunk(collider);
            for (int i = chunkRange[0]; i <= chunkRange[1]; i++) {
                List<Collider> chunk = new ArrayList<>(chunks.get(i));
                for (Collider toCheck : chunk) {
                    checkCollision(collider, toCheck);
                }
            }
        }
    }

    public int[] calculateChunk(Collider collider) {
        Rectangle colliderBound = collider.getBound();
        int firstChunk = (int) Math.round(colliderBound.getMinX() / 32.0);
        int secondChunk = (int) Math.round(colliderBound.getMaxX() / 32.0);
        return new int[]{firstChunk, secondChunk};
    }
    

    // TODO: podría ser más eficiente?
    public void addToChunk(Collider item) {
        int[] chunkRange = calculateChunk(item);
        if (chunkRange[0] >= chunks.size()) {
            for (int i = 0; i < chunkRange[0]; i++) {
                chunks.add(new Vector<>());
            }
        }

        for(int i = chunkRange[0]; i < chunks.size() && i < chunkRange[1]; i++) {
            chunks.get(i).add(item);
        }

        for(int i = chunks.size(); i < chunkRange[1]; i++) {
            chunks.add(new Vector<>());
            chunks.get(i).add(item);
        }
    }

    public void removeFromChunk(Collider item) {
        int[] chunkRange = calculateChunk(item);
        for(int i = chunkRange[0]; i < chunkRange[1] && i < chunks.size(); i++) {
            chunks.get(i).remove(item);
        }
    }

    public Iterable<Collider> getChunk(int ind) {
        return chunks.get(ind);
    }

    public int getAmountOfChunks() {
        return chunks.size();
    }

    public void registerToCheck(Collider c) { 
        nextCollidersToCheck.add(c);
    }

    public void addToCheck(Collider c) {
        nextCollidersToCheck.add(c);
    }
}
