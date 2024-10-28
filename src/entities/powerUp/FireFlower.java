package entities.powerUp;

import java.awt.Rectangle;

import colliders.powerUp.FireFlowerCollider;
import entities.BaseUpdatableEntity;
import graphics.GameGraphicElement;

public class FireFlower extends BaseUpdatableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "fireFlower";
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;

    protected FireFlowerCollider collider;
    protected GameGraphicElement graphicElement;

    public FireFlower() {
        points = 100;
        collider = new FireFlowerCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public FireFlower clone() {
        return new FireFlower();
    }

    public void switchDirection() {
        movingRight  = !movingRight;
    }

    public void update(){
        
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public FireFlowerCollider getCollider() {
        return collider;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
