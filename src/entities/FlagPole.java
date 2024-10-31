package entities;

import java.awt.Rectangle;

import colliders.FlagPoleCollider;
import graphics.GameGraphicElement;

public class FlagPole extends Body {
    protected static String SPRITES_FOLDER = "flagPole";

    protected FlagPoleCollider collider;
    protected GameGraphicElement graphicElement;

    public FlagPole() {
        collider = new FlagPoleCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public FlagPoleCollider getCollider() {
        return collider;
    }
}
