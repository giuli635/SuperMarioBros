package entities;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import colliders.MarioCollider;
import game.Game;
import game.KeyStatus;

public class Mario extends GameEntity {
    protected int speedX; 
    protected int speedY;
    protected int lifes;
    protected boolean loaded;
    protected boolean jumping;
    protected boolean onRight;
    protected boolean isJumping;
    protected boolean isGrounded;
    protected float gravity;

    public Mario() {
        super();
        speedX = 4;
        speedY = 0;
        jumping = false;
        collider = new MarioCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
        );
        onRight = true;
        gravity = 0.5f;
        isJumping = false;
        isGrounded = true; // Mario comienza en el suelo
        collider = new MarioCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/MarioRight.png"));
    }

    public Entity clone() {
        return new Mario();
    }

    public boolean getJumping() {
        return jumping;
    }

    public  void switchJumping() {
        jumping = !jumping;
    }

    public void update() {
        // Manejar el salto solo si Mario está en el suelo
        if (Game.instance().getKeyStatus(KeyEvent.VK_W) == KeyStatus.PRESSED && isGrounded) {
            startJump(); // este metodo inizializa los parametros del salto
        }

        if (isJumping) {
            applyGravity(); // este es el metodo que lo hace saltar
        }
        
        marioDirection(onRight);
        
        handleHorizontalMovement();// lo puse asi para que se vea bien

        graphicElement.translate(0, -3);
        collider.translate(0, -3);
    }

    public void marioDirection(boolean onRight) { 
        if(!isJumping) {
            if (onRight) {
                graphicElement.setSprite(new ImageIcon("sprites/MarioRight.png"));
            } else {
                graphicElement.setSprite(new ImageIcon("sprites/MarioLeft.png"));
            }
        }
        
    }

    public void startJump() {
        isJumping = true;
        isGrounded = false;
        speedY = 15;
        
        
        if (onRight) {
            graphicElement.setSprite(new ImageIcon("sprites/MarioJumpRight.png"));
        } else {
            graphicElement.setSprite(new ImageIcon("sprites/MarioJumpLeft.png"));
        }
    }

    public void applyGravity() {
        speedY -= gravity; 

        collider.translate(0, (int) speedY);
        graphicElement.translate(0, (int) speedY);
    }

    public void land() {
        isJumping = false;  // Detener el salto
        isGrounded = true;  // Indicar que Mario está en el suelo de nuevo
    }

    public boolean isFalling() {
        return !isGrounded;
    }

    public void handleHorizontalMovement() {
        // esto se ve horrible pero que se yo
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            if(!isJumping) {
                graphicElement.setSprite(new ImageIcon("sprites/MarioRunRight.gif")); 
            } else {
                graphicElement.setSprite(new ImageIcon("sprites/MarioJumpRight.png"));
            }
            graphicElement.translate(speedX, 0);
            collider.translate(speedX, 0);
            onRight = true;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            if(!isJumping) {
                graphicElement.setSprite(new ImageIcon("sprites/MarioRunLeft.gif"));
            } else {
                graphicElement.setSprite(new ImageIcon("sprites/MarioJumpLeft.png"));
            }
            graphicElement.translate(-speedX, 0);
            collider.translate(-speedX, 0);
            onRight = false;
        }
    }

}
