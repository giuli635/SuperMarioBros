package colliders;

import java.awt.Rectangle;

import colliders.updateables.UpdateableEntityCollider;
import collisions.CoinCollision;
import collisions.VisitorCollision;
import collisions.updateables.mario.MarioCollision;
import entities.updateables.Coin;
import game.SingletonGraphicEngine;
import game.SingletonSoundManager;
import utils.Axis;

public class CoinCollider extends BaseCollider implements UpdateableEntityCollider {
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
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public CoinCollision getCollision() {
        return new CoinCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        deactivate();
        getEntity().unload();
        SingletonGraphicEngine.instance().remove(getEntity().getGraphicElement());
        m.getCollider().getEntity().modifyPoints(Coin.POINTS);
        SingletonSoundManager.instance().playSound(Coin.SOUND);
    }

    public void handleVerticalCollision(MarioCollision m) {
        deactivate();
        getEntity().unload();
        SingletonGraphicEngine.instance().remove(getEntity().getGraphicElement());
        m.getCollider().getEntity().modifyPoints(Coin.POINTS);
        SingletonSoundManager.instance().playSound(Coin.SOUND);
    }
}
