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
    protected final static String MARIO_WALKING = "marioWalking";
    protected final static String MARIO_WALKING2 = "marioWalking2";

    protected static float maxSpeedX = 5;
    protected static float accelerationX = 0.1f;
    protected static float decelerationX = 0.18f;
    protected static float accelerationY = 0.5f;
    protected static float minSpeedX = 1;
    protected static float gravity = 2;
    protected boolean dead=false;
    protected float speedX;
    protected float speedY;
    protected int lives;
    protected boolean loaded;
    protected boolean jumping;
    protected Direction direction;
    protected boolean moving;
    protected boolean onRight;
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;

    public Mario() {
        speedX = minSpeedX;
        speedY = -3;
        loaded = false;
        jumping = false;
        direction = Direction.RIGHT;
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
        if (!dead) {
            handleHorizontalMovement();
            handleVerticalMovement();
        } else {
            die();
        }
    }

    protected void processDirection() {
        if (direction == Direction.LEFT) {
            if (!graphicElement.isFlipped()) {
                graphicElement.flipSprite();
            }
        }
    }

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
        boolean move = false;
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            move = true;
            direction = Direction.RIGHT;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            move = true;
            direction = Direction.LEFT;
        }

        int directionSign = direction == Direction.RIGHT ? 1 : -1;

        if (!move) {
            if (Math.abs(speedX) > 0) {
                if (Math.abs(speedX) < decelerationX) {
                    speedX = 0;
                } else {
                    speedX -= directionSign * decelerationX;
                }
            }
        } else {
            if (Math.abs(speedX) < maxSpeedX) {
                speedX += directionSign * accelerationX;
            }
            if (!jumping) {
                if (animationFrameCounter / framesPerSprite % 2 == 0) {
                    graphicElement.setSprite(MARIO_WALKING);
                } else {
                    graphicElement.setSprite(MARIO_WALKING2);
                }
            } else {
                graphicElement.setSprite(MARIO_JUMP);
            }

            animationFrameCounter++;
        }

        graphicElement.translate((int) speedX, 0);
        collider.translate((int) speedX, 0);
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
