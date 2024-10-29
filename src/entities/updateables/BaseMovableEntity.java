package entities.updateables;

public abstract class BaseMovableEntity extends UpdateableBody implements MovableEntity {
    public static final int GRAVITY = 3;
    protected int speedX;

    public void switchDirection() {
        speedX = -speedX;
        getGraphicElement().flipSprite();
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int x) {
        speedX = x;
    }

    public void applyGravity() {
        getGraphicElement().translate(0, -GRAVITY);
        getCollider().translate(0, -GRAVITY);
    }

    public void update() {
        getGraphicElement().translate(speedX, 0);
        getCollider().translate(speedX, 0);
        applyGravity();
    }
}
