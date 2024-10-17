package entities;

import javax.swing.ImageIcon;

import colliders.GoombaCollider;

public class Koopa_Troopa extends Enemy {
    private boolean movingRight = true;
    private int speedX = 1; // Velocidad horizontal

    public Koopa_Troopa(){
        super();
        speedX=2;
        collider = new GoombaCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/Koopa_troopa.png"));
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
        return new Koopa_Troopa();
    }

    @Override
    public void update() {
        // Movimiento horizontal
         int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);    
    }

}
