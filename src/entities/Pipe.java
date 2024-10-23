package entities;

import java.awt.Rectangle;

import game.Game;
import graphics.GameGraphicElement;
import colliders.PipeCollider;

public class Pipe extends BaseEntity {
    protected static String SPRITES_FOLDER = "pipe";

    public Pipe() {
        collider = new PipeCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER, Game.instance().getMode());
        graphicElement.setSprite(SPRITES_FOLDER);
    }
}
