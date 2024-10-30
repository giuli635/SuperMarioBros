package entities.updateables;

import java.awt.Rectangle;

import colliders.CoinCollider;
import graphics.GameGraphicElement;

public class Coin extends UpdateableBody {
    protected static String SPRITES_FOLDER = "coin";
    public final static int POINTS = 5;

    protected CoinCollider collider;
    protected GameGraphicElement graphicElement;
    
    public Coin() {
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

    @Override
    public void update() {
    }
}
