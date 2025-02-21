package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.GreenMushroomCollider;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class GreenMushroom extends BaseMovableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "greenMushroom";
    public final static int POINTS = 100;
    
    protected GreenMushroomCollider collider;
    protected GameGraphicElement graphicElement;

    public GreenMushroom() {
        speedX = 1;
        collider = new GreenMushroomCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public GreenMushroomCollider getCollider() {
        return collider;
    }
}
