package entities.updateables.enemies;

import java.awt.Rectangle;

import graphics.GameGraphicElement;

import colliders.updateables.enemies.GoombaCollider;
import entities.updateables.Animator;

public class Goomba extends BaseEnemy {
    protected static String SPRITES_FOLDER = "goomba";
    public final static String[] ANIMATED_SPRITES = {"goomba", "goombaWalking"};
    public static final int FRAMES_PER_SPRITE = 10;
    public final static int POINTS = 60;
    protected int speedX;

    protected GoombaCollider collider;
    protected GameGraphicElement graphicElement;
    protected Animator animator;

    public Goomba() {
        super();
        animator = new Animator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new GoombaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void update() {
        super.update();
        animator.animate();
    }

    @Override
    public void recieveDamage() {
        die(SPRITES_FOLDER + "Death");
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public GoombaCollider getCollider() {
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
