package entities.updateables.enemies;

import java.awt.Rectangle;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import colliders.updateables.enemies.PiranhaPlantCollider;
import entities.updateables.MovementAnimator;
import game.GraphicEngine;
import graphics.GameGraphicElement;

public class PiranhaPlant extends BaseEnemy {
    public static final float MAX_HEIGHT = 1.8f;
    public static String SPRITES_FOLDER = "piranha";
    public final static List<String> ANIMATED_SPRITES = List.of(
        "piranhaOpen", "piranhaClosed"
    );
    public final static int FRAMES_PER_SPRITE = 10;
    public final static int POINTS = 30;

    protected boolean movingUp;

    protected PiranhaPlantCollider collider;
    protected GameGraphicElement graphicElement;
    protected MovementAnimator animator;

    public PiranhaPlant() {
        super();
        animator = new MovementAnimator(ANIMATED_SPRITES, FRAMES_PER_SPRITE, this);
        collider = new PiranhaPlantCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        movingUp = true;
        setSprite("piranhaOpen");
        speedX = 0;
    }

    @Override
    public void recieveDamage() {
        collider.deactivate();
        unload();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
            }
        };

        timer.schedule(task, 1000);
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
