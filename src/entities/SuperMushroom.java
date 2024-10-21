package entities;

import javax.swing.ImageIcon;

import colliders.PowerUpCollider;

public class SuperMushroom extends PowerUp{
    protected int speedX;

    public SuperMushroom(){
        super();
        speedX = 1;
        collider = new PowerUpCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/superMushroom.png"));
    }

    public PowerUp clone() {
        return new SuperMushroom();
    }

    public void update() {
        move();
    }

    public void move() {
        collider.translate(-speedX, 0);
        graphicElement.translate(-speedX,0);
    }

    
    @Override
    public void apply(Mario m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }

}
