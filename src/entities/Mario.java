package entities;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import colliders.Direction;
import colliders.MarioCollider;
import game.Game;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class Mario extends BaseUpdatableEntity {
    protected int speedX; 
    protected int speedY;
    protected int lifes;
    protected boolean loaded;
    protected boolean jumping;
    protected Direction direction;
    protected float gravity;
    protected int jumpForce;

    public Mario() {
        speedX = 4;
        speedY = -3;
        gravity = 2;
        jumpForce = 10;
        loaded = false;
        jumping = false;
        collider = new MarioCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, new ImageIcon("sprites/mario.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
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

    public void land() {
        jumping = false;
        graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
    }

    protected void startJump() {
        jumping = true;
        jumpForce = 10;
        graphicElement.setSprite(new ImageIcon("sprites/marioJumping.png"));
    }

    protected void handleVerticalMovement() {
        if (speedY > -5) {
            speedY -= gravity; 
        }

        if (speedY > 20) {
            jumpForce = 0;
        }

        speedY += jumpForce;

        collider.translate(0, (int) speedY);
        graphicElement.translate(0, (int) speedY);
    }

    protected void handleHorizontalMovement() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            if(!jumping) {
                graphicElement.setSprite(new ImageIcon("sprites/marioRunning1.png")); 
            } else {
                graphicElement.setSprite(new ImageIcon("sprites/marioJumping.png"));
            }
            graphicElement.translate(speedX, 0);
            collider.translate(speedX, 0);
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            if(!jumping) {
                graphicElement.setSprite(new ImageIcon("sprites/marioRunning1.png"));
            } else {
                graphicElement.setSprite(new ImageIcon("sprites/marioJumping.png"));
            }
            graphicElement.translate(-speedX, 0);
            collider.translate(-speedX, 0);
        }
    }

}
