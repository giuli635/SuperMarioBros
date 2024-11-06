package colliders.updateables.powerups;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.powerups.GreenMushroomCollision;
import entities.updateables.mario.Mario;
import entities.updateables.powerups.GreenMushroom;
import utils.Axis;

public class GreenMushroomCollider extends PowerUpCollider {
    protected GreenMushroom m;

    public GreenMushroomCollider(GreenMushroom mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public GreenMushroom getEntity() {
        return m;
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public GreenMushroomCollision getCollision() {
        return new GreenMushroomCollision(this);
    }

    public void handleCollision(MarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.modifyPoints(GreenMushroom.POINTS);
        mario.addLife();
    }
}
