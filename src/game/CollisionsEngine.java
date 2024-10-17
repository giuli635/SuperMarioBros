package game;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import colliders.Collider;

public class CollisionsEngine {
    protected static CollisionsEngine uniqueInstance;
    protected List<Collider> nextCollidersToCheck;
    protected Set<Collider> toReset;
    protected NavigableMap<Integer, Set<Collider>> colliders;

    public CollisionsEngine() {
        nextCollidersToCheck = new LinkedList<>();
        colliders = new TreeMap<>();
        toReset = new HashSet<>();
    }

    public static CollisionsEngine instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollisionsEngine();
        }
        return uniqueInstance;
    }

    protected void checkCollision(Collider c1, Collider c2) {
        if (c1.getBound().intersects(c2.getBound())) {
            c2.sendCollision(c1.getCollision());
            c1.sendCollision(c2.getCollision());
        }
    }

    public void checkCollisions() {
        Queue<Collider> nextCollidersToCheckCopy = new LinkedList<>(nextCollidersToCheck);
        nextCollidersToCheck = new LinkedList<>();
        for (Collider collider : nextCollidersToCheckCopy) {
            int lowerBound = (int) collider.getBound().getX();
            int higherBound = (int) collider.getBound().getMaxX();
            for (Collider toCheck : getCollidersInRange(lowerBound, higherBound)) {
                checkCollision(collider, toCheck);
            }
            collider.resetVelocity();
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

    public Set<Collider> getCollidersInRange(int lowerBound, int higherBound) {
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

    public void addToReset(Collider c) {
        toReset.add(c);
    }
}
