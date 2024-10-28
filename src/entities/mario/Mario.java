package entities.mario;

import java.awt.Rectangle;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import colliders.MarioCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import entities.mario.actions.ActionComparator;
import entities.mario.actions.HorizontalMovement;
import entities.mario.actions.MarioAction;
import game.Game;
import game.GraphicEngine;
import game.LevelStats;
import entities.mario.actions.VerticalMovement;
import graphics.GameGraphicElement;

public class Mario extends BaseUpdatableEntity {
    public static final String MARIO_DEATH = "marioDeath";
    public static final String MARIO_CROUCHING = "marioCrouching";
    public final static String MARIO_STILL = "marioStill";
    public final static String MARIO_JUMP = "marioJumping";
    public final static String[] MARIO_WALKING = {"marioWalking", "marioWalking2"};
    public final static String MARIO_STOPPING = "marioStopping";

    public static final int FIXED_BOUNCE_SPEED = 15;

    protected float gravity = 1.5f;
    protected boolean falling;

    protected float jumpSpeed;
    protected float speedX;
    protected float speedY;
    protected boolean loaded;

    protected MarioCollider collider;
    protected GameGraphicElement graphicElement;
    protected SortedSet<MarioAction> actions;
    protected LevelStats levelStats;

    public Mario(LevelStats stats) {
        loaded = false;
        falling = false;
        levelStats = stats;
        collider = new MarioCollider(this, new Rectangle());
        actions = new TreeSet<>(new ActionComparator());
        addAction(new VerticalMovement());
        addAction(new HorizontalMovement());
        graphicElement = new GameGraphicElement(this, "mario");
        graphicElement.setSprite(MARIO_STILL);
        collider.setSize(
                graphicElement.getCurrentSprite().getIconWidth(),
                graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public MarioCollider getCollider() {
        return collider;
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    public Entity clone() {
        return new Mario(levelStats);
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean j) {
        falling = j;
    }

    public void update() {
        for (MarioAction action : actions) {
            action.execute(this);
        }

        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );

        graphicElement.translate((int) speedX, (int) speedY);
        collider.translate((int) speedX, (int) speedY);
        falling = true;
    }

    public void land() {
        falling = false;
        speedY = -gravity;
    }

    public void die() {
        collider.deactivate();
        graphicElement.setFolder("mario");
        graphicElement.setSprite(MARIO_DEATH);
        
        levelStats.decreaseLives();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
                Game.instance().resetCurrentLevel();
            }
        };

        timer.schedule(task,1000);
    }

    public void addPoints(int points){
        levelStats.addPoints(points);
    } 

    public void addSpeed(int dx, int dy) {
        speedX += dx;
        speedY += dy;
    }

    public void subtractPoints(int i) {
        levelStats.subtractPoints(i);
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void addAction(MarioAction action) {
        actions.add(action);
    }

    public void removeAction(MarioAction action) {
        actions.remove(action);
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}
