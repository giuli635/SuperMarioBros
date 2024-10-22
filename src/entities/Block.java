package entities;

import java.awt.Rectangle;

import colliders.BlockCollider;
import game.Game;
import graphics.GameGraphicElement;

public class Block extends BaseEntity {
    protected static String SPRITES_FOLDER = "block";

    public Block() {
        collider = new BlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
        graphicElement.setSprite(SPRITES_FOLDER);
    }
}
