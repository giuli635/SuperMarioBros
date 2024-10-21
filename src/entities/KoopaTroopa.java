package entities;

import javax.swing.ImageIcon;

import colliders.KoopaTroopaCollider;

public class KoopaTroopa extends Enemy {
    protected boolean movingRight = true;
    protected int speedX = 1;

    public KoopaTroopa() {
        super();
        speedX = 2;
        collider = new KoopaTroopaCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/koopaTroopa.png"));
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
        return new KoopaTroopa();
    }

    public void switchDirection() {
        movingRight = !movingRight;
    }

    @Override
    public void update() {
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);  
        graphicElement.translate(0, -3);
        collider.translate(0, -3);  
    }

}
