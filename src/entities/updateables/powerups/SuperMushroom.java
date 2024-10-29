package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.SuperMushroomCollider;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class SuperMushroom extends BaseMovableEntity implements PowerUp {
    public static final int POINTS_MARIO = 10;
    public static final int POINTS_SUPER_MARIO = 50;
    protected static String SPRITES_FOLDER = "superMushroom";

    protected SuperMushroomCollider collider;
    protected GameGraphicElement graphicElement;

    public SuperMushroom() {
        speedX = 1;
        collider = new SuperMushroomCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SuperMushroomCollider getCollider() {
        return collider;
    }
}
