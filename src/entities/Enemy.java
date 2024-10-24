package entities;

public interface Enemy extends UpdatableEntity {
    public void recieveDamage();
    public int getPoints();
    public Entity clone();
    public void update();
}
