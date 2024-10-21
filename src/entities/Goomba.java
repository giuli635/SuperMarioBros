package entities;

import javax.swing.ImageIcon;
import colliders.GoombaCollider;

public class Goomba extends Enemy {
    protected boolean movingRight = true;
    protected int speedX = 1; // Velocidad horizontal

    public Goomba() {
        super();
        speedX=2;
        collider = new GoombaCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/goomba.png"));
    }

    @Override
    public Entity clone() {
        return new Goomba();
    }

    @Override
    public void getDamage() {
        throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
    }

    @Override
    public int getPoints() {
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    public void switchDirection() {
        movingRight  = !movingRight;
    }

    @Override
    public void update() {
        // Movimiento horizontal
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);
        graphicElement.translate(0, -3);
        collider.translate(0, -3);

        
        // Verificar colisiones
       /*  CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        collisionsEngine.addToCheck(collider);*/
    }
    
}
