package entities.updateables.powerups;

import java.awt.Rectangle;
import java.util.List;

import colliders.updateables.powerups.FireFlowerCollider;
import entities.updateables.MovementAnimator;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class FireFlower extends BaseMovableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "fireFlower";
    public final static List<String> ANIMATED_SPRITES = List.of(
        "fireFlower", "fireFlower2"
    );
    public final static int FRAMES_PER_SPRITE = 10;
    public final static int POINTS_MARIO = 5;
    public final static int POINTS_SUPER_MARIO = 30;
    public final static int POINTS_FIRE_MARIO = 50;

    protected FireFlowerCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;

    public FireFlower() {
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
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
