package entities.updateables;

public interface MovableEntity {
    public void switchDirection();
    public int getSpeedX();
    public void setSpeedX(int x);
    public void applyGravity();
}

