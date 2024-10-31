package entities.updateables.enemies;

import java.awt.Rectangle;

import colliders.updateables.enemies.SpinyCollider;
import graphics.GameGraphicElement;

public class Spiny extends BaseEnemy {
    protected static String SPRITES_FOLDER = "spiny";
    public final static String[] ANIMATED_SPRITES = {"spiny", "spinyWalking"};

    protected SpinyCollider collider;
    protected GameGraphicElement graphicElement;

    public Spiny() {
        super();
        animatedSprites = ANIMATED_SPRITES;
        collider = new SpinyCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void recieveDamage() {
        die(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public SpinyCollider getCollider() {
        return collider;
    }
}
