package entities;

import java.awt.Rectangle;

import colliders.BlockCollider;
import graphics.GameGraphicElement;

public class Block implements Entity {
    protected static String SPRITES_FOLDER = "block";

    protected BlockCollider collider;
    protected GameGraphicElement graphicElement;
    
    public Block() {
        collider = new BlockCollider(this, new Rectangle(32, 32));
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        graphicElement.setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public BlockCollider getCollider() {
        return collider;
    }
}
