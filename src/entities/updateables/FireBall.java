package entities.updateables;

import java.awt.Rectangle;

import colliders.FireBallCollider;
import entities.updateables.mario.actions.ThrowFireBall;
import graphics.GameGraphicElement;

public class FireBall extends BaseMovableEntity implements Bouncer {
    protected static String SPRITES_FOLDER = "fireBall";
    public final static String[] ANIMATED_SPRITES = {"fireBall", "fireBall2"};
    protected ThrowFireBall thrower;
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int changingSprite;
    
    protected FireBallCollider collider;
    protected GameGraphicElement graphicElement;

    public FireBall(ThrowFireBall t) {
        thrower = t;
        speedX = 4;
        speedY = 0;
        animatedSprites = ANIMATED_SPRITES;
        collider = new FireBallCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    public void update(){
        speedY--;
        super.update();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public FireBallCollider getCollider() {
        return collider;
    }

    public void bounce() {
        speedY = 12;
    }

    public void destroy() {
        collider.deactivate();
        graphicElement.remove();
        unload();
        thrower.increaseAmmo();
    }
}
