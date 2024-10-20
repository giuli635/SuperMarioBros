package entities;

import javax.swing.ImageIcon;

import colliders.GoombaCollider;

public class KoopaTroopa extends Enemy {
    private boolean movingRight = true;
    private int speedX = 1; // Velocidad horizontal

    public KoopaTroopa(){
        super();
        speedX=2;
        collider = new GoombaCollider(this, collider.getBound());
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

    @Override
    public void update() {
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);    
    }

}
