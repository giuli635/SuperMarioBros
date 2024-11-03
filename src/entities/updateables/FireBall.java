package entities.updateables;

import java.awt.Rectangle;
import java.util.List;

import colliders.FireBallCollider;
import entities.updateables.mario.Mario;
import entities.updateables.mario.actions.ThrowFireBall;
import graphics.GameGraphicElement;

public class FireBall extends BaseMovableEntity implements Bouncer {
    protected static String SPRITES_FOLDER = "fireBall";
    public final static List<String> ANIMATED_SPRITES = List.of(
        "fireBall", "fireBall2"
    );
    public final static int FRAMES_PER_SPRITE = 10;
    protected ThrowFireBall thrower;
    
    protected FireBallCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;
    protected Mario mario;

    public FireBall(ThrowFireBall t, Mario m) {
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        thrower = t;
        speedX = 4;
        speedY = 0;
        mario = m;
        collider = new FireBallCollider(this, new Rectangle());
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
    }

    public Mario getMario() {
        return mario;
    }

    @Override
    public void unload() {
        super.unload();
        thrower.increaseAmmo();
    }
}
