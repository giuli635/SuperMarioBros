import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class CollisionsEngine {
    protected static CollisionsEngine uniqueInstance;
    protected Collection<Collider> nextCollidersToCheck;
    protected List<List<Collider>> chunks;
    protected static Direction[][] directionsMapping = {{Direction.RIGTH, Direction.LEFT}, {Direction.UP, Direction.DOWN}};
    protected int chunkCount;

    public CollisionsEngine() {
        nextCollidersToCheck = new ArrayList<>();
        chunks = new Vector<List<Collider>>();
        chunkCount = 0;
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
            
            c2.handleCollision(c1.getCollision(), dir);
            c1.handleCollision(c2.getCollision(), Direction.opposite(dir));
        }
    }

    public void checkCollisions() {
        for (Collider collider : nextCollidersToCheck) {
            int[] chunkRange = calculateChunk(collider);
            for (int i = chunkRange[0]; i <= chunkRange[1]; i++) {
                for (Collider toCheck : chunks.get(i)) {
                    checkCollision(collider, toCheck);
                }
            }
        }
    }

    public int[] calculateChunk(Collider collider) {
        Rectangle colliderBound = collider.getBound();
        int firstChunk = (int) (colliderBound.getMinX() % chunks.size());
        int secondChunk = (int) (colliderBound.getMaxX() % chunks.size());
        return new int[]{firstChunk, secondChunk};
    }
    

    public void addToChunk(int ind, Collider item) {
        if (ind >= chunks.size()) {
            for (int i = 0; i <= ind; i++) {
                chunks.add(new ArrayList<>());
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

    public int pixelChunkCount() {//ESTOS METODOS SON UN INTENTO DE DEAR DE MOVER LA PANTALLA AL LLEGAR AL FINAL DEL NIVEL
        return chunkCount*32;
    }

    public void incrementChunkCount() {
        chunkCount++;
    }


}
