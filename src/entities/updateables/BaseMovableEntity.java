package entities.updateables;

public abstract class BaseMovableEntity extends BaseAnimatedEntity  implements MovableEntity{
    public static final int GRAVITY = 3;
    protected int speedX;
    protected int speedY;

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

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int y) {
        speedY = y;
    }

    public void applyGravity() {
        getGraphicElement().translate(0, -GRAVITY);
        getCollider().translate(0, -GRAVITY);
    }

    public void update() {
        if (animatedSprites != null) {
            super.update();
        }
        getGraphicElement().translate(speedX, speedY);
        getCollider().translate(speedX, speedY);
        applyGravity();
    }
}
