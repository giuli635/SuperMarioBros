package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.StarCollider;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class Star extends BaseMovableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "star";
    public final static String[] STAR = {"star", "star2"};
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    protected int speedX;
    protected float speedY;

    protected StarCollider collider;
    protected GameGraphicElement graphicElement;

    public Star() {
        speedX = 2;
        speedY = 0;
        collider = new StarCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    public void update(){
        speedY = speedY - 0.5f;
        
        graphicElement.translate(speedX, (int) speedY);
        collider.translate(speedX, (int) speedY);
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

    public void startBounce() {
        speedY = 8;
    }

    private void setChangeableSprites() {
        changingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        changingSprite %= 2;
        animationFrameCounter++;
        setSprite(STAR[changingSprite]);
    }
}
