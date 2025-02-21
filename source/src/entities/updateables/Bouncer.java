package entities.updateables;

import colliders.Collider;
import graphics.GameGraphicElement;

public class Bouncer extends BaseMovableEntity {
    public void bounce() {
        speedY = 12;
    }

    @Override
    public void applyGravity() {
        getGraphicElement().translate(0, (int) -GRAVITY);
        getCollider().translate(0, (int) -GRAVITY);
    }

    @Override
    public void update() {
        getGraphicElement().translate((int) speedX, (int) speedY);
        getCollider().translate((int) speedX, (int) speedY);
        applyGravity();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return getGraphicElement();
    }

    @Override
    public Collider getCollider() {
        return getCollider();
    }
}
