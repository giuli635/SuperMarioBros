package entities.updateables.mario;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import colliders.updateables.mario.DefaultMarioCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.UpdateableBody;
import entities.updateables.mario.actions.ActionComparator;
import entities.updateables.mario.actions.HorizontalMovement;
import entities.updateables.mario.actions.MarioAction;
import entities.updateables.mario.actions.ResolveHorizontalMovementDirection;
import entities.updateables.mario.actions.ResolveSprite;
import entities.updateables.mario.actions.VerticalMovement;
import game.Game;
import game.LevelStats;
import game.SoundManager;
import graphics.GameGraphicElement;
import utils.Direction;

public class Mario extends UpdateableBody {
    public static final String MARIO_DEATH = "marioDeath";
    public static final String MARIO_CROUCHING = "marioCrouching";
    public final static String MARIO_STILL = "marioStill";
    public final static String MARIO_JUMP = "marioJumping";
    public final static String[] MARIO_WALKING = {"marioWalking", "marioWalking2"};
    public final static String MARIO_STOPPING = "marioStopping";

    public static final int FIXED_BOUNCE_SPEED = 15;
    public static final float GRAVITY = 1.5f;

    protected float jumpSpeed;
    protected float speedX;
    protected float speedY;
    protected float accelerationX;
    protected boolean falling;

    protected MarioCollider collider;
    protected GameGraphicElement graphicElement;
    protected SortedSet<MarioAction> actions;
    protected Stack<MarioState> states;
    protected LevelStats levelStats;
    protected Direction movementDirection;
    protected boolean overriteSprite;
    protected boolean loaded;

    public Mario(LevelStats stats) {
        loaded = false;
        falling = false;
        levelStats = stats;
        states = new Stack<>();
        collider = new DefaultMarioCollider(this, new Rectangle());
        actions = new TreeSet<>(new ActionComparator());
        movementDirection = Direction.NONE;

        addAction(new VerticalMovement());
        addAction(new ResolveHorizontalMovementDirection());
        addAction(new HorizontalMovement());
        addAction(new ResolveSprite());

        graphicElement = new GameGraphicElement(this, "mario");
        setSprite(MARIO_STILL);
    }

    @Override
    public MarioCollider getCollider() {
        return collider;
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    public void update() {
        Iterator<MarioAction> actionsIterator = actions.iterator();
        while (actionsIterator.hasNext()) {
            actionsIterator.next().execute(this);
        }

        graphicElement.translate((int) speedX, (int) speedY);
        collider.translate((int) speedX, (int) speedY);
        falling = true;
    }

    public void land() {
        falling = false;
        speedY = -GRAVITY;
    }

    public void die() {
        unload();
        collider.deactivate();
        setSpritesFolder("mario");
        setSprite(MARIO_DEATH);
        SoundManager.instance().removeAllSounds();
        SoundManager.instance().playSound("mariodie.wav");
        levelStats.decreaseLives();
        Game.instance().resetCurrentLevel();
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean j) {
        falling = j;
    }

    public void addSpeed(int dx, int dy) {
        speedX += dx;
        speedY += dy;
    }

    public void addPoints(int points){
        levelStats.addPoints(points);
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

    public float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public Direction getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(Direction movementDirection) {
        this.movementDirection = movementDirection;
    }

    public boolean overriteSprite() {
        return overriteSprite;
    }

    public void setOverriteSprite(boolean overriteSprite) {
        this.overriteSprite = overriteSprite;
    }

    public void setState(MarioState state) {
        states.push(state);
        state.setState();
    }

    public void removeState() {
        if (!states.isEmpty()) {
            states.pop().removeState();
        }
    }

    public void setCollider(MarioCollider c) {
        collider.deactivate();
        collider = c;
        collider.activate();
    }
}
