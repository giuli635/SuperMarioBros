package colliders.updateables.powerups;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import collisions.updateables.powerups.SuperMushroomCollision;
import entities.updateables.mario.Mario;
import entities.updateables.mario.states.SuperMario;
import entities.updateables.powerups.SuperMushroom;
import utils.Axis;

public class SuperMushroomCollider extends PowerUpCollider {
    protected SuperMushroom SM;

    public SuperMushroomCollider(SuperMushroom mushroom, Rectangle b) {
        super(b);
        SM = mushroom;
    }
    
    @Override
    public SuperMushroom getEntity() {
        return SM;
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public SuperMushroomCollision getCollision() {
        return new SuperMushroomCollision(this);
    }

    @Override
    public void handleCollision(SuperMarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.modifyPoints(SuperMushroom.POINTS_SUPER_MARIO);
    }

    @Override
    public void handleCollision(MarioCollision m) {
        super.handleCollision(m);
        Mario mario = m.getCollider().getEntity();
        mario.setState(new SuperMario(mario));
        mario.modifyPoints(SuperMushroom.POINTS_MARIO);
    }
}
