package entities.updateables;

public abstract class BaseMovableEntity extends UpdateableBody implements MovableEntity {
    public static final float GRAVITY = 3f;
    protected float speedX;
    protected float speedY;

    public void switchDirection() {
        speedX = -speedX;
        getGraphicElement().flipSprite();
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float x) {
        speedX = x;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float y) {
        speedY = y;
    }

    public void applyGravity() {
        getGraphicElement().translate(0, (int) -GRAVITY);
        getCollider().translate(0, (int) -GRAVITY);
    }

    public void update() {
        getGraphicElement().translate((int) speedX, (int) speedY);
        getCollider().translate((int) speedX, (int) speedY);
        applyGravity();
    }
}
