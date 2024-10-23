package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import colliders.Direction;
import colliders.MarioCollider;
import game.CollisionsEngine;
import game.Game;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class Mario extends BaseUpdatableEntity {
    protected static String MARIO = "mario";
    protected static String MARIO_JUMP = "marioJumping"; 
    protected static String MARIO_WALKING = "marioWalking";
    protected static String MARIO_WALKING2 = "marioWalking2";
    protected static float maxSpeedX = 5;
    protected static float accelerationX = 0.1f;
    protected static float decelerationX = 0.18f;
    protected static float accelerationY = 0.5f;
    protected static float minSpeedX = 1;
    protected static float gravity = 2;
    protected float speedX;
    protected float speedY;
    protected int lifes;
    protected boolean loaded;
    protected boolean jumping;
    protected Direction direction;
    protected int jumpForce;
    protected boolean dead;
    protected boolean moving;
    protected boolean onRight;
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;

    public Mario() {
        speedX = minSpeedX;
        speedY = -3;
        dead = false;
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
        if (!dead) {
            handleHorizontalMovement();
            handleVerticalMovement();
        } else {
            die();
        }
    }

    public void land() {
        jumping = false;
        if (onRight && !moving) {
            graphicElement.setSprite(MARIO);
        } else if (!moving && !onRight) {
            graphicElement.flipSprite(MARIO);
        }
    }

    protected void startJump() {
        jumping = true;
        speedY = 24;
        if (onRight) {
            graphicElement.setSprite(MARIO_JUMP);
        } else {
            graphicElement.flipSprite(MARIO_JUMP);
        }
    }

    protected void handleVerticalMovement() {
        if (speedY > -5) {
            speedY -= gravity;
        }

        collider.translate(0, (int) speedY);
        graphicElement.translate(0, (int) speedY);
    }

    protected void handleHorizontalMovement() {
        moving = false;


        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            moving = true;
            onRight = true;

            if (speedX < maxSpeedX) {
                speedX += accelerationX;
            }
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            moving = true;
            onRight = false;
            
            if (speedX > -maxSpeedX) {
                speedX -= accelerationX;
            }
        }

    
        if (!moving) {
            if (speedX > 0) {
                speedX -= decelerationX;
                if (speedX < 0) {
                    speedX = 0;
                }
            } else if (speedX < 0) {
                speedX += decelerationX;
                if (speedX > 0) {
                    speedX = 0;
                }
            }
        } else if (moving) {
            
            if (onRight && !jumping) {
                if (animationFrameCounter / framesPerSprite % 2 == 0) {
                    graphicElement.setSprite(MARIO_WALKING);
                } else {
                    graphicElement.setSprite(MARIO_WALKING2);
                }
            } else if (!onRight && !jumping) {
                if (animationFrameCounter / framesPerSprite % 2 == 0) {
                    graphicElement.flipSprite(MARIO_WALKING);
                } else {
                    graphicElement.flipSprite(MARIO_WALKING2);
                }
            }

            if (onRight && jumping) {
                graphicElement.setSprite(MARIO_JUMP);
            } else if (!onRight && jumping){
                graphicElement.flipSprite(MARIO_JUMP);
            }
    

            animationFrameCounter++;
        }
    

        graphicElement.translate((int) speedX, 0);
        collider.translate((int) speedX, 0);
    }

    public void die() {
        speedY -= gravity;
        graphicElement.setSprite("marioDeath");
        graphicElement.translate(0, (int) speedY);
    }

    public void takeDamage() {
        die();
        CollisionsEngine.instance().remove(collider);
        dead = true;
        speedY = 15;
    }

}
