package colliders.updateables.powerups;

import java.awt.Rectangle;

import colliders.updateables.mario.MarioCollider;
import colliders.updateables.mario.SuperMarioCollider;
import collisions.Collision;
import collisions.updateables.powerups.SuperMushroomCollision;
import entities.updateables.mario.SuperMario;
import entities.updateables.powerups.SuperMushroom;
import utils.Axis;

public class SuperMushroomCollider extends PowerUpCollider {
    protected SuperMushroom m;

    public SuperMushroomCollider(SuperMushroom mushroom, Rectangle b) {
        super(b);
        m = mushroom;
    }
    
    @Override
    public SuperMushroom getEntity() {
        return m;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public SuperMushroomCollision getCollision() {
        return new SuperMushroomCollision(this);
    }

    @Override
    public void handleCollision(SuperMarioCollider m) {
        super.handleCollision(m);
        m.getEntity().addPoints(SuperMushroom.POINTS_SUPER_MARIO);
    }

    @Override
    public void handleCollision(MarioCollider m) {
        super.handleCollision(m);
        m.getEntity().setState(new SuperMario(m.getEntity()));
    }
}
