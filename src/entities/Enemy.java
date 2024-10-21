package entities;

public interface Enemy extends UpdatableEntity {
    abstract public void getDamage();
    abstract public int getPoints();
    abstract public Entity clone();
    abstract public void update();
}
