package entities.updateables.enemies;

import java.awt.Rectangle;
import java.util.List;

import colliders.updateables.enemies.SpinyCollider;
import entities.updateables.MovementAnimator;
import graphics.GameGraphicElement;

public class Spiny extends BaseEnemy {
    protected static String SPRITES_FOLDER = "spiny";
    public final static List<String> ANIMATED_SPRITES = List.of(
        "spiny", "spinyWalking"
    );
    public final static int FRAMES_PER_SPRITE = 10;
    public final static int POINTS = 60;
    protected int speedX;

    protected SpinyCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;

    public Spiny() {
        super();
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void recieveDamage() {
        die(SPRITES_FOLDER);
    }

    @Override
    public void update() {
        super.update();
        animator.animate();
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SpinyCollider getCollider() {
        return collider;
    }

    @Override
    public int pointsToAdd() {
        return POINTS;
    }

    @Override
    public int pointsToSubtract() {
        return -(POINTS/2);
    }
}
