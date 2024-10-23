package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import colliders.Direction;
import colliders.MarioCollider;
import game.CollisionsEngine;
import game.Game;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class Mario extends BaseUpdatableEntity {
    protected static String SPRITES_FOLDER = "mario";
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
    protected boolean dead;
    protected boolean moving;

    public Mario() {
        speedX = minSpeedX;
        speedY = -3;
        dead = false;
        loaded = false;
        jumping = false;
        collider = new MarioCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
        graphicElement.setSprite(SPRITES_FOLDER);
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
        // graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
    }

    protected void startJump() {
        jumping = true;
        speedY = 24;
        // graphicElement.setSprite(new ImageIcon("sprites/marioJumping.png"));
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

            if (jumping) {
                // graphicElement.setSprite(new ImageIcon("sprites/marioRunning1.png"));
            } else {
                // graphicElement.setSprite(new ImageIcon("sprites/marioJumping.png"));
            }

            if (speedX < maxSpeedX) {
                speedX += accelerationX;
            }
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            moving = true;

            if (jumping) {
                // graphicElement.setSprite(new ImageIcon("sprites/marioRunning1.png"));
            } else {
                // graphicElement.setSprite(new ImageIcon("sprites/marioJumping.png"));
            }

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
        }

        graphicElement.translate((int) speedX, 0);
        collider.translate((int) speedX, 0);
    }

    public void die() {
        speedY -= gravity;
        // graphicElement.setSprite(new ImageIcon("sprites/marioDeath.png"));
        graphicElement.translate(0, (int) speedY);
    }

    public void takeDamage() {
        // aca va la logica que depende del estado
        die();
        CollisionsEngine.instance().remove(collider);
        dead = true;
        speedY = 15;
    }

}
