package entities;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import colliders.SuperMushroomCollider;
import graphics.GameGraphicElement;

public class SuperMushroom extends BaseUpdatableEntity {
    protected  int points;
    protected boolean movingRight = true;
    protected int speedX;

    public SuperMushroom() {
        super();
        points = 100;
        speedX = 1;
        collider = new SuperMushroomCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, new ImageIcon("sprites/superMushroom.png"));
        collider.setSize(
            graphicElement.getSprite().getIconWidth(),
            graphicElement.getSprite().getIconHeight()
        );
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
