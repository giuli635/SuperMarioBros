package entities.solids;

import java.awt.Rectangle;

import colliders.solids.BlockCollider;
import entities.Body;
import graphics.GameGraphicElement;

public class Block extends Body {
    protected static String SPRITES_FOLDER = "block";

    protected BlockCollider collider;
    protected GameGraphicElement graphicElement;
    
    public Block() {
        collider = new BlockCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
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
