package entities;

import javax.swing.ImageIcon;

import colliders.PowerUpCollider;

public class PowerUp extends GameEntity{
    protected int speedX;

    public PowerUp(){
        super();
        speedX = 1;
        collider = new PowerUpCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/SuperMushroom.gif"));
    }

    public PowerUp clone() {
        return new PowerUp();
    }

    public void update() {
        move();
    }

    public void move() {
        collider.translate(-speedX, 0);
        graphicElement.translate(-speedX,0);
    }

}
