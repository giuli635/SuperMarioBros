package entities.updateables;

public interface MovableEntity {
    public void switchDirection();
    public float getSpeedY();
    public void setSpeedY(float y);
    public float getSpeedX();
    public void setSpeedX(float x);
    public void applyGravity();
}

