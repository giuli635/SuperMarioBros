package entities;

import java.awt.Rectangle;

import colliders.CoinCollider;
import graphics.GameGraphicElement;

public class Coin extends BaseEntity {
    protected static String SPRITES_FOLDER = "coin";

    public Coin() {
        collider = new CoinCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
        collider.setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );
    }
}
