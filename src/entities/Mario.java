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
    protected int speedX;
    protected int speedY;
    protected int accelerationX;
    protected int decelerationX;
    protected int maxSpeed = 8;
    protected int lifes;
    protected boolean loaded;
    protected boolean jumping;
    protected Direction direction;
    protected float gravity;
    protected int jumpForce;
    protected boolean dead;
    protected boolean moving;
    protected boolean onRight;
    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;

    public Mario() {
        speedX = 4;
        speedY = -3;
        gravity = 2;
        jumpForce = 0;
        accelerationX = 1;
        decelerationX = 2;
        onRight = true;
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
        
        if (speedY > 20) {
            jumpForce = 0;
        }
        
        //speedY += jumpForce;

        collider.translate(0, (int) speedY);
        graphicElement.translate(0, (int) speedY);
    }

    protected void handleHorizontalMovement() {
        moving = false;
        

        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            moving = true;
            onRight = true;

            if (speedX < maxSpeed) {
                speedX += accelerationX;
            }
        }
    
  
        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            moving = true;
            onRight = false;

            if (speedX > -maxSpeed) {
                speedX -= accelerationX;
            }
        }
    
        
        if (!moving) {
            if (speedX > 0) {
                speedX -= decelerationX;
                if (speedX < 0) speedX = 0;
            } else if (speedX < 0) {
                speedX += decelerationX;
                if (speedX > 0) speedX = 0;
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

        graphicElement.translate(speedX, 0);
        collider.translate(speedX, 0);
    }

    public void die() {
        speedY -= gravity;
        //graphicElement.setSprite(new ImageIcon("sprites/marioDeath.png"));
        graphicElement.translate(0, speedY);
    }

    public void takeDamage() {
        //aca va la logica que depende del estado
        die();
        CollisionsEngine.instance().remove(collider);
        dead = true;
        speedY = 15;
    }

}
