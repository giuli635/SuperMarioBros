package entities.updateables.enemies;

import java.awt.Rectangle;

import graphics.GameGraphicElement;

import colliders.updateables.enemies.GoombaCollider;

public class Goomba extends BaseEnemy {
    protected static String SPRITES_FOLDER = "goomba";
    public final static String[] ANIMATED_SPRITES = {"goomba", "goombaWalking"};


    protected GoombaCollider collider;
    protected GameGraphicElement graphicElement;

    public Goomba() {
        super();
        animatedSprites = ANIMATED_SPRITES;
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
