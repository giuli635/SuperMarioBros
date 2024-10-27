package entities.powerUp;

import java.awt.Rectangle;

import colliders.powerUp.FireFlowerCollider;
import colliders.powerUp.StarCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import graphics.GameGraphicElement;

public class Star extends BaseUpdatableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "star";
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;

    protected StarCollider collider;
    protected GameGraphicElement graphicElement;

    public Star() {
        points = 100;
        speedX = 1;
        collider = new StarCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public Entity clone() {
        return new Star();
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
    public StarCollider getCollider() {
        return collider;
    }

    @Override
    public void disappear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disappear'");
    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }
}
