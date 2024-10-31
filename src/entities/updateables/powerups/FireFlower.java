package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.FireFlowerCollider;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class FireFlower extends BaseMovableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "fireFlower";
    public final static String[] ANIMATED_SPRITES = {"fireFlower", "fireFlower2"};
    protected FireFlowerCollider collider;
    protected GameGraphicElement graphicElement;

    public FireFlower() {
        animatedSprites = ANIMATED_SPRITES;
        speedX = 0;
        collider = new FireFlowerCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public FireFlowerCollider getCollider() {
        return collider;
    }
}
