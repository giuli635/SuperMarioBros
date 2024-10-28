package entities.powerUp;

import java.awt.Rectangle;

import colliders.powerUp.GreenMushroomCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import entities.mario.MarioState;
import graphics.GameGraphicElement;

public class GreenMushroom extends BaseUpdatableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "greenMushroom";
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;
    protected MarioState state;

    protected GreenMushroomCollider collider;
    protected GameGraphicElement graphicElement;

    public GreenMushroom() {
        points = 100;
        speedX = 1;
        collider = new GreenMushroomCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
        load();
    }

    @Override
    public Entity clone() {
        return new GreenMushroom();
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
    public GreenMushroomCollider getCollider() {
        return collider;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
