package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import colliders.KoopaTroopaCollider;
import graphics.GameGraphicElement;

public class KoopaTroopa extends BaseUpdatableEntity implements Enemy {
    protected boolean movingRight = true;
    protected int speedX = 1;

    public KoopaTroopa(){
        speedX=2;
        collider = new KoopaTroopaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this);
        graphicElement.setSprite(new ImageIcon("sprites/koopaTroopa.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
        );
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
