package entities.updateables;

public interface MovableEntity {
    public void switchDirection();
    public int getSpeedY();
    public void setSpeedY(int y);
    public int getSpeedX();
    public void setSpeedX(int x);
    public void applyGravity();
}

