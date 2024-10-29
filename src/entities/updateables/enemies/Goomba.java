package entities.updateables.enemies;

import java.awt.Rectangle;

import graphics.GameGraphicElement;

import colliders.updateables.enemies.GoombaCollider;

public class Goomba extends BaseEnemy {
    protected static String SPRITES_FOLDER = "goomba";
    protected int speedX;

    protected GoombaCollider collider;
    protected GameGraphicElement graphicElement;

    public Goomba() {
        super();
        collider = new GoombaCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public void recieveDamage() {
        die(SPRITES_FOLDER + "Death");
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public GoombaCollider getCollider() {
        return collider;
    }
    
}
