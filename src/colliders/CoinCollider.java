package colliders;

import java.awt.Rectangle;

import collisions.CoinCollision;
import collisions.Collision;
import collisions.updateables.mario.MarioCollision;
import entities.updateables.Coin;
import entities.updateables.powerups.PowerUp;
import game.GraphicEngine;
import game.SoundManager;
import utils.Axis;

public class CoinCollider extends BaseCollider {
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
    public void recieveCollision(Collision c, Axis a) {
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
        m.getCollider().getEntity().modifyPoints(Coin.POINTS);
        SoundManager.instance().playSound(Coin.SOUND);
    }

    public void handleVerticalCollision(MarioCollision m) {
        deactivate();
        getEntity().unload();
        GraphicEngine.instance().remove(getEntity().getGraphicElement());
        m.getCollider().getEntity().modifyPoints(Coin.POINTS);
        SoundManager.instance().playSound(Coin.SOUND);
    }
}
