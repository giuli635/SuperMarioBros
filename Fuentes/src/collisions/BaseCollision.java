package collisions;

public abstract class BaseCollision implements VisitorCollision {
    protected boolean managed;

    public BaseCollision() {
        managed = true;
    }

    public boolean wasManaged() {
        return managed;
    }

    public void setManaged(boolean managed) {
        this.managed = managed;
    }
}
