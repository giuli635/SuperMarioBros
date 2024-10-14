package game;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import colliders.Collider;
import colliders.Direction;
import colliders.MarioCollider;
import colliders.ScreenBorderCollider;
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
        for (Collider collider : nextCollidersToCheck) {
            int[] chunkRange = calculateChunk(collider);
            for (int i = chunkRange[0]; i <= chunkRange[1]; i++) {
                List<Collider> chunk = new ArrayList<>(chunks.get(i));
                for (Collider toCheck : chunk) {
                    checkCollision(collider, toCheck);
                    
                    if (toCheck instanceof ScreenBorderCollider) {
                        System.out.println("Borde");
                        System.out.println("X: " + toCheck.getPosition().getX());
                        System.out.println("Y: "+toCheck.getPosition().getY());
                        System.out.println("Chunk: "+i);
                    }
                    else if (toCheck instanceof MarioCollider) {
                        System.out.println("Mario");
                        System.out.println("X: " + toCheck.getPosition().getX());
                        System.out.println("Y: "+toCheck.getPosition().getY());
                        System.out.println("Chunk: "+i);
                    }
                }
            }
        }
        nextCollidersToCheck.removeAll(nextCollidersToCheck);
    }

    public int[] calculateChunk(Collider collider) {
        Rectangle colliderBound = collider.getBound();
        int firstChunk = (int) (colliderBound.getMinX() / 32.0);
        int secondChunk = (int) (colliderBound.getMaxX() / 32.0);
        return new int[]{firstChunk, secondChunk};
    }
    

    public void addToChunk(int ind, Collider item) {
        if (ind >= chunks.size()) {
            for (int i = 0; i <= ind; i++) {
                chunks.add(new Vector<>());
            }
        }

        chunks.get(ind).add(item);
    }

    public void removeFromChunk(Collider item) {
        int[] chunkRange = calculateChunk(item);
        for(int i = chunkRange[0]; i < chunkRange[1]; i++) {
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
