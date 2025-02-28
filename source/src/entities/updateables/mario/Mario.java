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
import entities.updateables.mario.actions.StrategyMarioAction;
import entities.updateables.mario.actions.ResolveHorizontalMovementDirection;
import entities.updateables.mario.actions.ResolveSprite;
import entities.updateables.mario.actions.VerticalMovement;
import entities.updateables.mario.states.CommandMarioStatus;
import game.SingletonCollisionsEngine;
import game.SingletonGame;
import game.Stats;
import game.SingletonSoundManager;
import graphics.GameGraphicElement;
import utils.Direction;
import utils.PriorityComparator;

public class Mario extends UpdateableBody {
    protected static final List<Map<Color, Color>> COLOR_STAR_MARIO_COLORS = initStarColor();

    protected static List<Map<Color, Color>> initStarColor() {
        Map<Color, Color> firstColorMapping = Map.of(
            // Black to green
            new Color(0, 0, 0, 255), new Color(12, 147, 0, 255),
            // Grey to orange 
            new Color(107, 109, 0, 255), new Color(234, 158, 34, 255),
            // Red to green
            new Color(181, 49, 32, 255), new Color(12, 147, 0, 255),
            // Orange to white
            new Color(234, 158, 34, 255), new Color(255, 254, 255, 255)
        );

        Map<Color, Color> secondColorMapping = Map.of(
            // Black to red
            new Color(0, 0, 0, 255), new Color(181, 49, 32, 255),
            // Grey to orange
            new Color(107, 109, 0, 255), new Color(234, 158, 34, 255),
            // Orange to white
            new Color(234, 158, 34, 255), new Color(255, 254, 255, 255)
        );

        Map<Color, Color> thirdColorMapping = Map.of(
            // Black to blue 
            new Color(0, 0, 0, 255), new Color(0, 124, 141),
            // Grey to brown
            new Color(107, 109, 0, 255), new Color(153, 78, 0, 255),
            // Red to black
            new Color(181, 49, 32, 255), new Color(0, 0, 0, 255),
            // Orange to pale rose
            new Color(234, 158, 34, 255), new Color(254, 204, 197, 255)
        );

        return List.of(firstColorMapping, secondColorMapping, thirdColorMapping);
    }

    public static final String MARIO_DEATH = "marioDeath";
    public static final String MARIO_CROUCHING = "marioCrouching";
    public final static String MARIO_STILL = "marioStill";
    public final static String MARIO_JUMP = "marioJumping";
    public final static List<String> MARIO_WALKING = List.of(
        "marioWalking", "marioWalking2"
    );
    public final static String MARIO_STOPPING = "marioStopping";

    public static final int FIXED_BOUNCE_SPEED = 15;
    public static final float GRAVITY = 1.5f;

    protected float jumpSpeed;
    protected float speedX;
    protected float speedY;
    protected float accelerationX;
    protected boolean falling;

    protected List<Map<Color, Color>> starMarioColors;
    protected MarioCollider collider;
    protected GameGraphicElement graphicElement;
    protected SortedSet<StrategyMarioAction> actions;
    protected Map<Integer, CommandMarioStatus> states;
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
        starMarioColors = COLOR_STAR_MARIO_COLORS;

        addAction(new VerticalMovement(this));
        addAction(new ResolveHorizontalMovementDirection(this));
        addAction(new HorizontalMovement(this));
        addAction(new ResolveSprite(this));

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
        Iterator<StrategyMarioAction> actionsIterator = actions.iterator();
        while (actionsIterator.hasNext()) {
            actionsIterator.next().execute();
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
        SingletonSoundManager.instance().removeAllSounds();
        SingletonSoundManager.instance().playSound("mariodie.wav");
        stats.decreaseLives();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                SingletonGame.instance().checkGameOver();
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

    public void addAction(StrategyMarioAction action) {
        actions.add(action);
    }

    public void removeAction(StrategyMarioAction action) {
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

    public List<Map<Color, Color>> getColorStarMarioColors() {
        return starMarioColors;
    }

    public void setStarMarioColors(List<Map<Color, Color>> initialColorStarMario) {
        this.starMarioColors = initialColorStarMario;
    }

    public void modifyPoints(int points) {
        stats.modifyPoints(points);
    }

    public void addLife(){
        stats.addLife();
    }

    public void setState(CommandMarioStatus state) {
        CommandMarioStatus previousState = states.put(state.getPriority(), state);
        if (previousState != null) {
            previousState.removeStatus();
        }

        state.setStatus();
    }

    public void removeState(CommandMarioStatus state) {
        states.remove(state.getPriority());
        state.removeStatus();
    }

    public MarioCollider setCollider(MarioCollider colliderToSet) {
        SingletonGame.instance().setDebugging(true);
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
        oldCollider.setColliderOnTop(null);
    }

    public void replaceCollider(MarioCollider c) {
        collider.deactivate();
        SingletonCollisionsEngine.instance().swap(collider, c);
        collider = c;
        collider.activate();
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
