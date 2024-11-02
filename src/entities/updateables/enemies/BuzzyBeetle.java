package entities.updateables.enemies;

import java.awt.Rectangle;
import java.util.List;

import graphics.GameGraphicElement;

import colliders.updateables.enemies.BuzzyBeetleCollider;
import entities.updateables.MovementAnimator;

public class BuzzyBeetle extends BaseEnemy implements ShellEnemy {
    protected static String SPRITES_FOLDER = "buzzyBeetle";
    public final static List<String> ANIMATED_SPRITES = List.of(
        "buzzyBeetle", "buzzyBeetleWalking"
    );
    public final static int FRAMES_PER_SPRITE = 10;
    public final static int POINTS = 30; 

    protected boolean shell;
    protected BuzzyBeetleCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;

    public BuzzyBeetle() {
        super();
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        shell = false;
        collider = new BuzzyBeetleCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public BuzzyBeetle clone() {
        return new BuzzyBeetle();
    }

    @Override
    public void recieveDamage() {
        if (!shell) {
            setSprite(SPRITES_FOLDER + "Shell");
            speedX = 0;
            shell = true;
        } else {
            die(SPRITES_FOLDER + "Shell");
        }
    }

    public void update() {
        if (!shell) {
            animator.animate();
        }
        super.update();
    }

    public void setShell(boolean b) {
        shell = b;
    }

    public boolean getShell() {
        return shell;
    }
    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public BuzzyBeetleCollider getCollider() {
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
