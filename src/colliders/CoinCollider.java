package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.CoinCollision;
import collisions.Collision;
import collisions.MarioCollision;
import entities.Coin;
import game.GraphicEngine;

public class CoinCollider extends BaseCollider{
    protected Coin coin;

    public CoinCollider(Coin c, Rectangle b) {
        super(b);
        coin = c;
    }

    @Override
    public Coin getEntity() {
        return coin;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public CoinCollision getCollision() {
        return new CoinCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        deactivate();
        getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
        m.getCollider().getEntity().addPoints(Coin.POINTS);
    }

    public void handleVerticalCollision(MarioCollision m) {
        deactivate();
        getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
        m.getCollider().getEntity().addPoints(Coin.POINTS);
    }
}
