package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import colliders.GoombaCollider;
import graphics.GameGraphicElement;

public class Goomba extends BaseUpdatableEntity implements Enemy {
    private boolean movingRight = true;
    private int speedX = 1; // Velocidad horizontal

    public Goomba() {
        speedX=2;
        collider = new GoombaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this);
        graphicElement.setSprite(new ImageIcon("sprites/goomba.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
        );
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
    }
    
}
