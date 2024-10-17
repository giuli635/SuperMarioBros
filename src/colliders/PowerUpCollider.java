package colliders;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import collisions.Collision;
import collisions.MarioCollision;
import collisions.PowerUpCollision;
import entities.Entity;
import entities.Mario;
import entities.PowerUp;
import game.CollisionsEngine;
import game.GraphicEngine;
import graphics.GraphicElement;


public class PowerUpCollider extends BaseCollider{
    protected PowerUp powerUp;

    public PowerUpCollider(PowerUp pw, Rectangle b) {
        super(b);
        powerUp = pw;
    }

    public Entity getEntity() {
        return powerUp;
    }

    public Collision getCollision() {
        return new PowerUpCollision(this);
    }

    public void sendCollision(Collision c) {
        c.collide(this);
    }

    public void handleCollision(MarioCollision c) {
        MarioCollider collider = c.getCollider();
        Mario mario = (Mario) collider.getEntity();
        GraphicElement graphicElement = collider.getEntity().getGraphicElement();
        CollisionsEngine collisionsEngine = CollisionsEngine.instance();
        GraphicEngine graphicEngine = GraphicEngine.instance();

        
        

        collisionsEngine.remove(this);
        graphicEngine.removeGraphicElement(this.getEntity().getGraphicElement());
        
        System.out.println("Hubo Collision");
    }

    

}
