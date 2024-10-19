package entities;

import javax.swing.ImageIcon;
import colliders.SpinyCollider;
import game.CollisionsEngine;


public class Spiny extends Enemy {
    private boolean movingRight = true;
    private int speedX = 1;

    public Spiny() {
        super();
        speedX = 2;
        collider = new SpinyCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/Spiny.png"));
    }

    @Override
    public void getDamage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public Entity clone() {
       return new Spiny();
    }

    @Override
    public void update() {
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);
    }
}
