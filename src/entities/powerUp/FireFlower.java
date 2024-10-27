package entities.powerUp;

import java.awt.Rectangle;

import colliders.powerUp.FireFlowerCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import entities.mario.MarioState;
import entities.mario.SuperMario;
import graphics.GameGraphicElement;

public class FireFlower extends BaseUpdatableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "fireFlower";
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;
    protected MarioState state;

    protected FireFlowerCollider collider;
    protected GameGraphicElement graphicElement;

    public FireFlower() {
        points = 100;
        state = new SuperMario(); //Cambiar
        collider = new FireFlowerCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public Entity clone() {
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

    public MarioState getState() {
        return state;
    }
}
