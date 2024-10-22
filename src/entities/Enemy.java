package entities;

public interface Enemy extends UpdatableEntity {
    public void getDamage();
    public int getPoints();
    public Entity clone();
    public void update();
}
