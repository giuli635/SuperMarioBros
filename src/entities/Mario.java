package entities;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import colliders.MarioCollider;
import game.CollisionsEngine;
import game.Game;
import game.KeyStatus;

public class Mario extends GameEntity {
    protected int speedX; 
    protected int speedY;
    protected int lifes;
    protected boolean loaded;

    public Mario() {
        super();
        speedX = 4;
        speedY = 9;
        collider = new MarioCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
        );
    }

    public Entity clone() {
        return new Mario();
    }
    public void update() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            graphicElement.translate(speedX, 0);
            collider.translate(speedX, 0);
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            graphicElement.translate(-speedX, 0);
            collider.translate(-speedX, 0);
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_W) == KeyStatus.PRESSED) {
            collider.translate(0, speedY);
            graphicElement.translate(0, speedY);
        }

        graphicElement.translate(0, -3);
        collider.translate(0, -3);
        CollisionsEngine.instance().addToCheck(collider);
    }
    


}
