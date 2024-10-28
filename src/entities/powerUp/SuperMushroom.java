package entities.powerUp;

import java.awt.Rectangle;

import colliders.powerUp.SuperMushroomCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import graphics.GameGraphicElement;

public class SuperMushroom extends BaseUpdatableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "superMushroom";
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;

    protected SuperMushroomCollider collider;
    protected GameGraphicElement graphicElement;

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

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SuperMushroomCollider getCollider() {
        return collider;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
