package entities.solids;

import java.awt.Rectangle;

import colliders.solids.BrickCollider;
import entities.Body;
import graphics.GameGraphicElement;

public class Brick extends Body {
    protected static String SPRITES_FOLDER = "brick";

    protected BrickCollider collider;
    protected GameGraphicElement graphicElement;

    public Brick() {
        collider = new BrickCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
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
