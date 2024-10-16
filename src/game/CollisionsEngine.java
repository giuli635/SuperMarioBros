package game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import colliders.Collider;
import colliders.Direction;
import colliders.Vector2D;

public class CollisionsEngine {
    protected static CollisionsEngine uniqueInstance;
    protected Collection<Collider> nextCollidersToCheck;
    protected NavigableMap<Integer, Set<Collider>> colliders;

    public CollisionsEngine() {
        nextCollidersToCheck = new ArrayList<>();
        colliders = new TreeMap<>();
    }

    public static CollisionsEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollisionsEngine();
        }
        return uniqueInstance;
    }

    protected void checkCollision(Collider c1, Collider c2) {
        if (c1.getBound().intersects(c2.getBound())) {
            Vector2D collisionVector = c1.getVelocity().sum(c2.getVelocity());
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
            int lowerBound = (int) collider.getBound().getX();
            int higherBound = (int) collider.getBound().getMaxX();
            for (Collider toCheck : getCollidersInRange(lowerBound, higherBound)) {
                checkCollision(collider, toCheck);
            }
        }
    }

    protected void addPoint(int x, Collider item) {
        Set<Collider> set = colliders.get(x);
        if (set == null) {
            set = new HashSet<>();
            colliders.put(x, set);
        }
        set.add(item);
    }

    protected void removePoint(int x, Collider item) {
        Set<Collider> set = colliders.get(x);
        if (set != null) {
            set.remove(item);
        }
    }

    public void add(Collider item) {
        addPoint((int) item.getBound().getX(), item);
        addPoint((int) item.getBound().getMaxX(), item);
    }

    public void remove(Collider item) {
        removePoint((int) item.getBound().getX(), item);
        removePoint((int) item.getBound().getMaxX(), item);
    }

    public void registerToCheck(Collider c) { 
        nextCollidersToCheck.add(c);
    }

    public void addToCheck(Collider c) {
        nextCollidersToCheck.add(c);
    }

    public Iterable<Collider> getCollidersInRange(int lowerBound, int higherBound) {
        Collection<Set<Collider>> toFlatten = colliders.subMap(lowerBound, higherBound).values();
        Set<Collider> setColliders = new HashSet<>();
        for (Set<Collider> set : toFlatten) {
            setColliders.addAll(set);
        }
        return setColliders;
    }

    public int getAmoutColliders() {
        return colliders.size();
    }
}
