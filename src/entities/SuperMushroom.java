package entities;

import javax.swing.ImageIcon;

import colliders.SuperMushroomCollider;

public class SuperMushroom extends GameEntity {
    protected  int points;
    protected boolean movingRight = true;
    protected int speedX;

    public SuperMushroom() {
        super();
        points = 100;
        speedX = 1;
        collider = new SuperMushroomCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/superMushroom.png"));
    }

    @Override
    public Entity clone() {
        return new SuperMushroom();
    }

    public void switchDirection() {
        movingRight  = !movingRight;
    }

    public void update(){
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);
        graphicElement.translate(0, -3);
        collider.translate(0, -3);
    }

}
