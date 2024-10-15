package entities;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import colliders.MarioCollider;
import game.CollisionsEngine;
import game.Game;
import game.KeyStatus;

public class Mario extends GameEntity {
    protected Point position;
    protected int speedX; 
    protected int lifes;
    protected boolean loaded;

    public Mario() {
        super();
        speedX = 3;
        collider = new MarioCollider(this, collider.getBound());
        position = collider.getPosition();
        graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
    }

    public Entity clone() {
        return new Mario();
    }

    public void update() {
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            graphicElement.translate(speedX, 0);
            collider.translate(speedX, 0);
        } else if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            graphicElement.translate(-speedX, 0);
            collider.translate(-speedX, 0);
        }

        CollisionsEngine.instance().addToCheck(collider);
    }

}
