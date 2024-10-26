package entities;

import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.BrickCollider;

public class Brick implements Entity {
    protected static String SPRITES_FOLDER = "brick";

    protected BrickCollider collider;
    protected GameGraphicElement graphicElement;

    public Brick() {
        collider = new BrickCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public BrickCollider getCollider() {
        return collider;
    }
}
