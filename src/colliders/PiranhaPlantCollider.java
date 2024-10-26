package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import collisions.PiranhaPlantCollision;
import entities.PiranhaPlant;

public class PiranhaPlantCollider extends BaseCollider implements EnemyCollider {
    protected PiranhaPlant piranha;

    public PiranhaPlantCollider(PiranhaPlant p, Rectangle b) {
        super(b);
        piranha = p;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
       c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new PiranhaPlantCollision(this);
    }

    @Override
    public PiranhaPlant getEntity() {
        return piranha;
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        if(collisionDirection == Direction.DOWN && m.getCollider().getEntity().getJumping()) { //TODO: make this more robust
            piranha.recieveDamage();
            m.getCollider().getEntity().addVelocity(0, 8);
        } else {
            m.getCollider().getEntity().die();
        }
    }
}
