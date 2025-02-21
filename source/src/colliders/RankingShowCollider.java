package colliders;

import java.awt.Rectangle;

import colliders.solids.SolidCollider;
import colliders.updateables.UpdateableEntityCollider;
import collisions.VisitorCollision;
import collisions.RankingShowCollision;
import collisions.updateables.mario.MarioCollision;
import entities.ConfigurationBlock;
import entities.updateables.mario.Mario;
import game.SingletonGame;
import utils.Axis;

public class RankingShowCollider extends SolidCollider implements UpdateableEntityCollider {
    protected ConfigurationBlock block;

    public RankingShowCollider(ConfigurationBlock q, Rectangle b) {
        super(b);
        block = q;
    }

    @Override
    public ConfigurationBlock getEntity() {
        return block;
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public VisitorCollision getCollision() {
        return new RankingShowCollision(this);
    }
    
    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();
        int displacement = displaceVertically(m.getCollider());
        
        if (displacement >= 0) {
            mario.land();
        } else {
            translate(0, -displacement);
            getEntity().getGraphicElement().translate(0, -displacement);
            mario.setSpeedY(0);
            fallBackIntoPlace(displacement);

            SingletonGame.instance().showRanking();
        }
    }
}
