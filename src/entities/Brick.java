package entities;

import java.awt.Rectangle;

import game.Game;
import graphics.GameGraphicElement;
import colliders.BrickCollider;

public class Brick extends BaseEntity {
    protected static String SPRITES_FOLDER = "brick";

    public Brick() {
        collider = new BrickCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
        graphicElement.setSprite(SPRITES_FOLDER);
    }
}
