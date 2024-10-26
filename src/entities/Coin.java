package entities;

import java.awt.Rectangle;

import colliders.CoinCollider;
import graphics.GameGraphicElement;

public class Coin implements Entity {
    protected static String SPRITES_FOLDER = "coin";

    protected CoinCollider collider;
    protected GameGraphicElement graphicElement;
    
    public Coin() {
        collider = new CoinCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public CoinCollider getCollider() {
        return collider;
    }
}
