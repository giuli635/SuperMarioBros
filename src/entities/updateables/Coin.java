package entities.updateables;

import java.awt.Rectangle;

import colliders.CoinCollider;
import graphics.GameGraphicElement;

public class Coin extends BaseAnimatedEntity {
    protected static String SPRITES_FOLDER = "coin";
    public final static String[] ANIMATED_SPRITES = {"coin2", "coin3"};
    public static final String SOUND = "coin.wav";
    public final static int POINTS = 5;

    protected CoinCollider collider;
    protected GameGraphicElement graphicElement;
    
    public Coin() {
        animatedSprites = ANIMATED_SPRITES;
        collider = new CoinCollider(this, new Rectangle());
        graphicElement = new GameGraphicElement(this, SPRITES_FOLDER);
        setSprite(SPRITES_FOLDER);
    }

    @Override
    public GameGraphicElement getGraphicElement() {
        return graphicElement;
    }

    @Override
    public CoinCollider getCollider() {
        return collider;
    }

    
}
