package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.CoinCollision;
import collisions.Collision;
import entities.Coin;

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
    public Collision getCollision() {
        return new CoinCollision(this);
    }
    
}
