package entities;

import javax.swing.ImageIcon;

import colliders.GoombaCollider;
import game.CollisionsEngine;

public class Goomba extends Enemy {
    private boolean movingRight = true;
    private int speedX = 1; // Velocidad horizontal

    public Goomba(){
        super();
        speedX=2;
        collider = new GoombaCollider(this, collider.getBound());
        graphicElement.setSprite(new ImageIcon("sprites/Goomba.png"));

    }

    @Override
    public Entity clone() {
        return new Goomba();
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
    public void update() {
        // Movimiento horizontal
        int moveX = movingRight ? speedX : -speedX;
        graphicElement.translate(moveX, 0);
        collider.translate(moveX, 0);

        
        // Verificar colisiones
       /*  CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        collisionsEngine.addToCheck(collider);*/
    }
    
}
