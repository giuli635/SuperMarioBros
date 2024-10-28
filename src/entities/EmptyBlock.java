package entities;


import java.awt.Rectangle;

import graphics.GameGraphicElement;
import colliders.EmptyBlockCollider;

public class EmptyBlock implements Entity {
    protected static String SPRITES_FOLDER = "EmptyBlock";

    protected EmptyBlockCollider collider;
    protected GameGraphicElement graphicElement;

    public EmptyBlock() {
        collider = new EmptyBlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public EmptyBlockCollider getCollider() {
        return collider;
    }
}