package entities;

import java.awt.Rectangle;

import colliders.SuperMushroomCollider;
import graphics.GameGraphicElement;

public class SuperMushroom extends BaseUpdatableEntity {
    protected static String SPRITES_FOLDER = "superMushroom";
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;

    public SuperMushroom() {
        points = 100;
        speedX = 1;
        collider = new SuperMushroomCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public Entity clone() {
        return new SuperMushroom();
    }

    public void switchDirection() {
        movingRight  = !movingRight;
    }

    public void update(){
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);

        graphicElement.translate(0, -3);
        collider.translate(0, -3);
    }
}
