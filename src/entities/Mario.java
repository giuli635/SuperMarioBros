package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import colliders.Direction;
import colliders.MarioCollider;
import game.CollisionsEngine;
import game.Game;
import game.GraphicEngine;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class Mario extends BaseUpdatableEntity {
    protected final static String MARIO = "mario";
    protected final static String MARIO_JUMP = "marioJumping";
    protected final static String[] MARIO_WALKING = {"marioWalking", "marioWalking2"};
    protected final static String MARIO_STOPPING = "marioStopping";

    protected static float maxSpeedX = 6;
    protected static float accelerationX = 0.1f;
    protected static float decelerationX = 0.18f;
    protected static float accelerationY = 0.5f;
    protected static float minSpeedX = 1;
    protected static float gravity = 2;

    protected float speedX;
    protected float speedY;
    protected int lives;
    protected boolean loaded;
    protected boolean jumping;
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int walkingSprite;

    public Mario() {
        speedX = 0;
        speedY = -3;
        loaded = false;
        jumping = false;
        collider = new MarioCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, MARIO, Game.instance().getMode());
        graphicElement.setSprite(MARIO);
        collider.setSize(
                graphicElement.getCurrentSprite().getIconWidth(),
                graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    public Entity clone() {
        return new Mario();
    }

    public boolean getJumping() {
        return jumping;
    }

    public void setJumping(boolean j) {
        jumping = j;
    }

    public void update() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_W) == KeyStatus.PRESSED && !jumping) {
            startJump();
        }
        handleHorizontalMovement();
        handleVerticalMovement();
    }

    // protected void processDirection() {
    //     if (direction == Direction.LEFT) {
    //         if (!graphicElement.isFlipped()) {
    //             graphicElement.flipSprite();
    //         }
    //     }
    // }

    public void land() {
        jumping = false;
        graphicElement.setSprite(MARIO);
    }

    protected void startJump() {
        jumping = true;
        speedY = 24;
        graphicElement.setSprite(MARIO_JUMP);
    }

    protected void handleVerticalMovement() {
        if (speedY > -5) {
            speedY -= gravity;
        }

        collider.translate(0, (int) speedY);
        graphicElement.translate(0, (int) speedY);
    }

    protected void handleHorizontalMovement() {
        Direction currentDirection = Direction.horizontalDirectionFromSign((int) speedX);
        Direction movementDirection = Direction.NONE;

        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            movementDirection = Direction.RIGHT;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            movementDirection = Direction.sum(movementDirection, Direction.LEFT);
        }

        if (!jumping) {
            if (movementDirection != Direction.NONE) {
                if (currentDirection == Direction.NONE || movementDirection == currentDirection) {
                    if (Math.abs(speedX) < maxSpeedX) {
                        speedX += Direction.signFromDirection(movementDirection) * accelerationX;
                    }

                    setWalkingSprites();
                } else {
                    declerate(currentDirection, 2);
                    graphicElement.setSprite(MARIO_STOPPING);
                }
            } else {
                declerate(currentDirection, 1);
            }
        } else {
            graphicElement.setSprite(MARIO_JUMP);
        }

        graphicElement.translate((int) speedX, 0);
        collider.translate((int) speedX, 0);
    }

    protected void setWalkingSprites() {
        walkingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        walkingSprite %= 2;
        graphicElement.setSprite(MARIO_WALKING[walkingSprite]);
        animationFrameCounter++;
    }

    protected void declerate(Direction currentDirection, int rate) {
        if (Math.abs(Math.floor(speedX)) < decelerationX) {
            speedX = 0;
            graphicElement.setSprite(MARIO);
        } else {
            speedX -= rate * Direction.signFromDirection(currentDirection) * decelerationX;
            setWalkingSprites();
        }
    }

    public void die() {
        Game.instance().unregisterToUpdate(this);
        CollisionsEngine.instance().remove(collider);
        graphicElement.setSprite("marioDeath");
        //TODO: deathAnimation();
        GraphicEngine.instance().removeGraphicElement(graphicElement);
    }

    public void addVelocity(int dx, int dy) {
        speedX += dx;
        speedY += dy;
    }

    public int getLives(){
        return lives;
    }
}
