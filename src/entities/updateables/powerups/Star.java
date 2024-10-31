package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.StarCollider;
import entities.updateables.BaseMovableEntity;
import entities.updateables.Bouncer;
import graphics.GameGraphicElement;

public class Star extends BaseMovableEntity implements PowerUp, Bouncer {
    protected static String SPRITES_FOLDER = "star";
    public final static String[] ANIMATED_SPRITES = {"star", "star2"};
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    protected StarCollider collider;
    protected GameGraphicElement graphicElement;

    public Star() {
        speedX = 4;
        speedY = 0;
        animatedSprites = ANIMATED_SPRITES;
        collider = new StarCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    public void update(){
        if (speedY > -2 * BaseMovableEntity.GRAVITY) {
            speedY--;
        }
        super.update();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public StarCollider getCollider() {
        return collider;
    }

    public void bounce() {
        speedY = 12;
    }
}
