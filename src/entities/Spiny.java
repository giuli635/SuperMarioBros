package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import colliders.SpinyCollider;
import graphics.GameGraphicElement;

public class Spiny extends BaseUpdatableEntity implements Enemy {
    protected boolean movingRight = true;
    protected int speedX = 1;

    public Spiny() {
        super();
        speedX = 2;
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this);
        graphicElement.setSprite(new ImageIcon("sprites/spiny.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
        );
    }

    @Override
    public void getDamage() {
        throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
    }

    @Override
    public int getPoints() {
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public Entity clone() {
       return new Spiny();
    }

    public void switchDirection() {
        movingRight  = !movingRight;
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
