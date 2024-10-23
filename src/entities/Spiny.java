package entities;

import java.awt.Rectangle;

import colliders.SpinyCollider;
import game.Game;
import graphics.GameGraphicElement;

public class Spiny extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "spiny";
    protected boolean movingRight = true;
    protected int speedX = 1;

    public Spiny() {
        super();
        speedX = 2;
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
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

    public void spawn(int x, int y) {
        graphicElement.setPosition(x, y);
        collider.setPosition(x, y);

        System.out.println("Spiny Position: " + graphicElement.getPosition());
    }
}
