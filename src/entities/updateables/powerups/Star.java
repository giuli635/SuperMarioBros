package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.StarCollider;
import entities.updateables.Animator;
import entities.updateables.BaseMovableEntity;
import entities.updateables.Bouncer;
import graphics.GameGraphicElement;

public class Star extends BaseMovableEntity implements PowerUp, Bouncer {
    public static final String SPRITES_FOLDER = "star";
    public static final String[] ANIMATED_SPRITES = {"star", "star2"};
    public static final int FRAMES_PER_SPRITE = 10;
    
    protected StarCollider collider;
    protected GameGraphicElement graphicElement;
    protected Animator animator;

    public Star() {
        speedX = 4;
        speedY = 0;
        animator = new Animator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new StarCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    public void update(){
        if (speedY > -2 * BaseMovableEntity.GRAVITY) {
            speedY--;
        }
        super.update();
        animator.animate();
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
