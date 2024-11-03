package entities.updateables.mario;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import colliders.updateables.mario.DefaultMarioCollider;
import colliders.updateables.mario.MarioCollider;
import entities.updateables.UpdateableBody;
import entities.updateables.mario.actions.HorizontalMovement;
import entities.updateables.mario.actions.MarioAction;
import entities.updateables.mario.actions.ResolveHorizontalMovementDirection;
import entities.updateables.mario.actions.ResolveSprite;
import entities.updateables.mario.actions.VerticalMovement;
import entities.updateables.mario.states.MarioState;
import game.CollisionsEngine;
import game.Game;
import game.Stats;
import game.SoundManager;
import graphics.GameGraphicElement;
import utils.Direction;
import utils.PriorityComparator;

public class Mario extends UpdateableBody {
    protected static final Map<Color, Color> INITIAL_COLOR_STAR_MARIO = initStarColor();

    protected static Map<Color, Color> initStarColor() {
        Map<Color, Color> colorMapping = Map.of(
            new Color(0, 0, 0, 255), new Color(12, 147, 0, 255),
            new Color(107, 109, 0, 255), new Color(234, 158, 34, 255),
            new Color(181, 49, 32, 255), new Color(12, 147, 0, 255),
            new Color(234, 158, 34, 255), new Color(255, 254, 255, 255)
        );

        return colorMapping;
    }

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

    protected Map<Color, Color> initialColorStarMario;
    protected MarioCollider collider;
    protected GameGraphicElement graphicElement;
    protected SortedSet<MarioAction> actions;
    protected Map<Integer, MarioState> states;
    protected Stats stats;
    protected Direction movementDirection;
    protected boolean overriteSprite;
    protected boolean loaded;

    public Mario(Stats s) {
        loaded = false;
        falling = false;
        stats = s;
        collider = new DefaultMarioCollider(this, new Rectangle());
        actions = new TreeSet<>(new PriorityComparator());
        states = new HashMap<>();
        movementDirection = Direction.NONE;
        initialColorStarMario = INITIAL_COLOR_STAR_MARIO;

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

        translate((int) speedX, (int) speedY);
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
        stats.decreaseLives();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                Game.instance().resetCurrentLevel();
            }
        }; 
        timer.schedule(task,3000);
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

    public Map<Color, Color> getInitialColorStarMario() {
        return initialColorStarMario;
    }

    public void setInitialColorStarMario(Map<Color, Color> initialColorStarMario) {
        this.initialColorStarMario = initialColorStarMario;
    }

    public void setState(MarioState state) {
        MarioState previousState = states.put(state.getPriority(), state);
        if (previousState != null) {
            previousState.removeState();
        }

        state.setState();
    }

    public void removeState(MarioState state) {
        states.remove(state.getPriority());
        state.removeState();
    }

    public MarioCollider setCollider(MarioCollider colliderToSet) {
        MarioCollider nextCollider = collider;
        while (nextCollider.getPriority() > colliderToSet.getPriority()) {
            nextCollider = nextCollider.getBaseCollider();
        }

        colliderToSet.copy(nextCollider);
        if (nextCollider.getPriority() == colliderToSet.getPriority()) {
            swapCollider(nextCollider, colliderToSet);
        } else {
            putColliderOnTop(colliderToSet, nextCollider);
        }

        return nextCollider;
    }

    protected void putColliderOnTop(MarioCollider newTopCollider, MarioCollider bottomCollider) {
        MarioCollider previousTopCollider = bottomCollider.getColliderOnTop();
        if (previousTopCollider == null) {
            newTopCollider.setBaseCollider(bottomCollider);
            replaceCollider(newTopCollider);
        } else {
            newTopCollider.setBaseCollider(bottomCollider);
            newTopCollider.setColliderOnTop(previousTopCollider);
        }
    }

    protected void swapCollider(MarioCollider oldCollider, MarioCollider newCollider) {
        newCollider.setBaseCollider(oldCollider.getBaseCollider());
        if (oldCollider.getColliderOnTop() != null) {
            oldCollider.getColliderOnTop().setBaseCollider(newCollider);
        } else {
            replaceCollider(newCollider);
        }
    }

    public void replaceCollider(MarioCollider c) {
        collider.deactivate();
        collider = c;
        if (collider.isColliding()) {
            CollisionsEngine.instance().swap(c);
        }
        collider.activate();
    }

    public void modifyPoints(int points) {
        stats.modifyPoints(points);
    }

    public void addLives(){
        stats.addLives();
    }

    public void removeCollider(MarioCollider colliderToRemove) {
        MarioCollider currentCollider = collider;
        while (currentCollider != colliderToRemove && currentCollider != null) {
            currentCollider = collider.getBaseCollider();
        }

        if (currentCollider != null) {
            MarioCollider topCollider = colliderToRemove.getColliderOnTop();
            colliderToRemove.getBaseCollider().setColliderOnTop(topCollider);

            if (topCollider == null) {
                replaceCollider(collider.getBaseCollider());
            }
        }
    }
}
