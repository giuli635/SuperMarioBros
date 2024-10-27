package entities.enemies;

import java.awt.Rectangle;

import colliders.enemies.SpinyCollider;
import entities.BaseUpdatableEntity;
import entities.Entity;
import graphics.GameGraphicElement;

public class Spiny extends BaseUpdatableEntity implements Enemy {
    protected static String SPRITES_FOLDER = "spiny";
    protected int speedX;

    protected SpinyCollider collider;
    protected GameGraphicElement graphicElement;

    public Spiny() {
        super();
        speedX = -2;
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public void recieveDamage() {
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
        speedX = -speedX;
        graphicElement.flipSprite();
    }

    @Override
    public void update() {
        graphicElement.translate(speedX, 0);
        collider.translate(speedX, 0);
        graphicElement.translate(0, -3);
        collider.translate(0, -3);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SpinyCollider getCollider() {
        return collider;
    }
}
