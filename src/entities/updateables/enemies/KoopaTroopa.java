package entities.updateables.enemies;

import java.awt.Rectangle;

import colliders.updateables.enemies.KoopaTroopaCollider;
import entities.updateables.Animator;
import graphics.GameGraphicElement;

public class KoopaTroopa extends BaseEnemy implements ShellEnemy {
    protected static String SPRITES_FOLDER = "koopa";
    public final static String[] ANIMATED_SPRITES = {"koopa", "koopaWalking"};
    public static final int FRAMES_PER_SPRITE = 10;
    public final static int POINTS = 90;
    protected boolean shell;

    protected KoopaTroopaCollider collider;
    protected GameGraphicElement graphicElement;
    protected Animator animator;

    public KoopaTroopa(){
        super();
        animator = new Animator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        shell = false;
        collider = new KoopaTroopaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
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

    @Override
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
    public KoopaTroopaCollider getCollider() {
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
