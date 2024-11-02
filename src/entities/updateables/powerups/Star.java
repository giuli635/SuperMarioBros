package entities.updateables.powerups;

import java.awt.Rectangle;
import java.util.List;

import colliders.updateables.powerups.StarCollider;
import entities.updateables.MovementAnimator;
import entities.updateables.BaseMovableEntity;
import entities.updateables.Bouncer;
import graphics.GameGraphicElement;

public class Star extends BaseMovableEntity implements PowerUp, Bouncer {
    public static final String SPRITES_FOLDER = "star";
    public static final List<String> ANIMATED_SPRITES = List.of(
        "star", "star2"
    );
    public static final int FRAMES_PER_SPRITE = 10;
    
    protected StarCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;

    public Star() {
        speedX = 4;
        speedY = 0;
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
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
