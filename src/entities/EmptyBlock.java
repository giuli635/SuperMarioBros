package entities;

import java.awt.Rectangle;

import colliders.invisibles.EmptyBlockCollider;
import graphics.GameGraphicElement;

public class EmptyBlock extends Body {
    protected static String SPRITES_FOLDER = "EmptyBlock";

    protected EmptyBlockCollider collider;
    protected GameGraphicElement graphicElement;

    public EmptyBlock() {
        collider = new EmptyBlockCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
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
