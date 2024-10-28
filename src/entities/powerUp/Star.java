package entities.powerUp;

import java.awt.Rectangle;

import colliders.powerUp.StarCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import graphics.GameGraphicElement;

public class Star extends BaseUpdatableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "star";
    public final static String[] STAR = {"star", "star2"};
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    protected int points;
    protected boolean movingRight = true;
    protected int speedX;
    protected float speedY;

    protected StarCollider collider;
    protected GameGraphicElement graphicElement;

    public Star() {
        points = 100;
        speedX = 2;
        speedY = 0;
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
        bounce();
        setChangeableSprites();
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
    public int getPoints() {
        return points;
    }

    public void bounce() {
        
        speedY = speedY - 0.5f;
        int moveX = movingRight ? speedX : -speedX;
        
        graphicElement.translate(moveX, (int) speedY);
        collider.translate(moveX, (int) speedY);
    }

    public void startBounce() {
        speedY = 8;
    }

    private void setChangeableSprites() {
        changingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        changingSprite %= 2;
        animationFrameCounter++;
        graphicElement.setSprite(STAR[changingSprite]);
    }
}
