package entities.updateables.enemies;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.enemies.PiranhaPlantCollider;
import entities.updateables.Animator;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class PiranhaPlant extends BaseEnemy {
    public static final float MAX_HEIGHT = 1.5f;
    public static String SPRITES_FOLDER = "piranha";
    public final static String[] ANIMATED_SPRITES = {"piranhaOpen", "piranhaClosed"};
    public static final int FRAMES_PER_SPRITE = 10;
    public final static int POINTS = 30;

    protected boolean movingUp;

    protected PiranhaPlantCollider collider;
    protected GameGraphicElement graphicElement;
    protected Animator animator;

    public PiranhaPlant() {
        super();
        animator = new Animator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new PiranhaPlantCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        movingUp = true;
        setSprite("piranhaOpen");
        speedX = 0;
    }

    @Override
    public void recieveDamage() {
        collider.deactivate();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
                unload();
            }
        };

        timer.schedule(task,1000);
    }

    @Override
    public void update() {
        handleVerticalMovement();
        translate((int) speedX, (int) speedY);
        animator.animate();
    }

    private void handleVerticalMovement() {
        if (movingUp) {
            speedY = speedY + 0.01f;
            if (speedY >= MAX_HEIGHT) {
                switchDirection();
                speedY = 0;
            }
        } else {
            speedY = speedY - 0.01f;
            if (speedY <= -MAX_HEIGHT) {
                switchDirection();
                speedY = 0;
            }
        }
    }

    public void switchDirection() {
        movingUp = !movingUp;
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public PiranhaPlantCollider getCollider() {
        return collider;
    }

    @Override
    public int pointsToAdd() {
        return POINTS;
    }

    @Override
    public int pointsToSubtract() {
        return -(POINTS);
    }
}
