package entities;

import entities.solids.Block;
import graphics.GameGraphicElement;

public class Castle extends Block{
    protected static final String SPRITES_FOLDER = "castle";
    protected String sprite;

    public Castle(char c) {
        if (c == 'C') {
            sprite = "bigCastle";
        } else {
            sprite = "smallCastle";
        }

        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(sprite);
    }
}
