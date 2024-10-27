package entities.mario;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import colliders.Direction;
import colliders.MarioCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import entities.mario.actions.ActionComparator;
import entities.mario.actions.MarioAction;
import game.CollisionsEngine;
import game.Game;
import game.GraphicEngine;
import game.KeyStatus;
import game.LevelReader;
import game.LevelStats;
import graphics.GameGraphicElement;

public class Mario extends BaseUpdatableEntity {
    protected final static String MARIO_STILL = "marioStill";
    protected final static String MARIO_DEATH = "marioDeath";
    protected final static String MARIO_JUMP = "marioJumping";
    protected final static String[] MARIO_WALKING = {"marioWalking", "marioWalking2"};
    protected final static String MARIO_STOPPING = "marioStopping";

    protected static float maxSpeedX = 6;
    protected static float accelerationX = 0.1f;
    protected static float decelerationX = 0.18f;
    protected static float minSpeedX = 2;

    protected static float accelerationYWithW = 1f;
    protected static float accelerationY = 0.2f;
    protected static float gravity = 1.5f;
    protected static int initialSpeedY = 12;
    protected static int minSpeedY = -8;
    protected boolean falling;

    protected float jumpSpeed;
    protected float speedX;
    protected float speedY;
    protected boolean loaded;

    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int walkingSprite;

    protected MarioCollider collider;
    protected GameGraphicElement graphicElement;
    protected SortedSet<MarioAction> actions;
    protected boolean listenToKeys;
    protected LevelStats levelStats;

    public boolean isListeningToKeys() {
        return listenToKeys;
    }

    public void listenToKeys(boolean listenToKeys) {
        this.listenToKeys = listenToKeys;
    }

    public Mario(LevelStats lStats) {
        loaded = false;
        falling = false;
        listenToKeys = true;
        levelStats = lStats;
        collider = new MarioCollider(this, new Rectangle());
        actions = new TreeSet<>(new ActionComparator());
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
        Direction currentDirection = Direction.horizontalDirectionFromSign((int) speedX);
        Direction movementDirection = Direction.NONE;
        String newSprite;
        
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            movementDirection = Direction.RIGHT;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            movementDirection = Direction.sum(movementDirection, Direction.LEFT);
        }

        handleVerticalMovement();

        if (!falling) {
            newSprite = handleGroundHorizontalMovement(movementDirection, currentDirection);
        } else {
            newSprite = handleAirHorizontalMovement(movementDirection, currentDirection);
        }

        resolveSpriteDirection(movementDirection, newSprite);
        falling = true;
    }

    protected String handleAirHorizontalMovement(Direction movementDirection, Direction currentDirection) {
        if (speedX < minSpeedX)  {
            speedX = minSpeedX;
        }

        if (movementDirection == currentDirection || currentDirection == Direction.NONE) {
            speedX = Direction.signFromDirection(movementDirection) * Math.abs(speedX);
        } else {
            speedX = 0;
        }

        graphicElement.translate((int) speedX, (int) speedY);
        collider.translate((int) speedX, (int) speedY);
        return MARIO_JUMP;
    }

    protected void handleVerticalMovement() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_W) == KeyStatus.PRESSED) {
            if (falling) {
                if (speedY > 0) {
                    speedY += accelerationYWithW - gravity;
                } else if (speedY > minSpeedY){
                    speedY -= gravity;
                }
            } else {
                speedY = initialSpeedY;
            }
        } else {
            if (speedY > 0) {
                speedY += accelerationY - gravity;
            } else if (speedY > minSpeedY){
                speedY -= gravity;
            }
        }
    }

    protected String handleGroundHorizontalMovement(Direction movementDirection, Direction currentDirection) {
        String newSprite = MARIO_STILL;

        if (movementDirection != Direction.NONE) {
            if (currentDirection == Direction.NONE || movementDirection == currentDirection) {
                if (speedX == 0) {
                    speedX = Direction.signFromDirection(movementDirection) * minSpeedX;
                } else {
                    if (Math.abs(speedX) < maxSpeedX) {
                        speedX += Direction.signFromDirection(movementDirection) * accelerationX;
                    }
                }

                newSprite = setWalkingSprites();
            } else {
                if (Math.floor(Math.abs(speedX)) < 2 * decelerationX) {
                    speedX = 0;
                } else {
                    speedX -= 2 * Direction.signFromDirection(currentDirection) * decelerationX;
                    newSprite = MARIO_STOPPING;
                }
            }
        } else {
            if (Math.floor(Math.abs(speedX)) < decelerationX) {
                speedX = 0;
            } else {
                speedX -= Direction.signFromDirection(currentDirection) * decelerationX;
                newSprite = setWalkingSprites();
            }
        }

        graphicElement.translate((int) speedX, (int) speedY);
        collider.translate((int) speedX, (int) speedY);
        return newSprite;
    }

    protected void resolveSpriteDirection(Direction movementDirection, String newSprite) {
        boolean flipped = graphicElement.isFlipped();
        graphicElement.setSprite(newSprite);
        collider.setSize(
                graphicElement.getCurrentSprite().getIconWidth(),
                graphicElement.getCurrentSprite().getIconHeight()
        );
        if (movementDirection == Direction.LEFT || movementDirection == Direction.NONE && flipped) {
            graphicElement.flipSprite();
        }
    }

    protected String setWalkingSprites() {
        walkingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        walkingSprite %= 2;
        animationFrameCounter++;
        return MARIO_WALKING[walkingSprite];
    }

    public void land() {
        falling = false;
        speedY = -gravity;
    }

    public void die() {
        Game.instance().unregisterToUpdate(this);
        CollisionsEngine.instance().remove(collider);
        graphicElement.setFolder("mario");
        graphicElement.setSprite(MARIO_DEATH);
        
        levelStats.decreasedLives();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run(){
                GraphicEngine.instance().remove(graphicElement);
                //Game.instance().resetCurrentLevel();
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
}
