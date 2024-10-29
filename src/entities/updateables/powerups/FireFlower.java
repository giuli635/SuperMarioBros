package entities.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.powerups.FireFlowerCollider;
import entities.updateables.BaseMovableEntity;
import graphics.GameGraphicElement;

public class FireFlower extends BaseMovableEntity implements PowerUp {
    protected static String SPRITES_FOLDER = "fireFlower";

    protected FireFlowerCollider collider;
    protected GameGraphicElement graphicElement;

    public FireFlower() {
        speedX = 0;
        collider = new FireFlowerCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    public void update(){
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
