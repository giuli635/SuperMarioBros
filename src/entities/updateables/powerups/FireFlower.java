package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.FireFlowerCollider;
import entities.updateables.Animator;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class FireFlower extends BaseMovableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "fireFlower";
    public final static String[] ANIMATED_SPRITES = {"fireFlower", "fireFlower2"};
    public final static int FRAMES_PER_SPRITE = 10;

    protected FireFlowerCollider collider;
    protected GameGraphicElement graphicElement;
    protected Animator animator;

    public FireFlower() {
        animator = new Animator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        speedX = 0;
        collider = new FireFlowerCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void update() {
        animator.animate();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public FireFlowerCollider getCollider() {
        return collider;
    }
}
