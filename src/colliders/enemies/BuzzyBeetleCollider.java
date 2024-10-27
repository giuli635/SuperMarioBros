package colliders.enemies;

import java.awt.Rectangle;

import colliders.Direction;
import collisions.Axis;
import collisions.Collision;
import collisions.MarioCollision;
import entities.enemies.BuzzyBeetle;
import collisions.BuzzyBeetleCollision;

public class BuzzyBeetleCollider extends EnemyCollider {
    protected BuzzyBeetle buzzyBeetle;

    public BuzzyBeetleCollider(BuzzyBeetle z, Rectangle b) {
        super(b);
        buzzyBeetle = z;
    }

    @Override
    public BuzzyBeetle getEntity() {
        return buzzyBeetle;
    }

    @Override
    public BuzzyBeetleCollision getCollision() {
        return new BuzzyBeetleCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        if (!buzzyBeetle.getShell()) {
            m.getCollider().getEntity().die();
            m.getCollider().getEntity().subtractPoints(getEntity().subtractPoints());
        } else if(buzzyBeetle.getSpeedX() == 0) {
            int displacement = m.getCollider().displaceX(collision, 3);
            m.getCollider().getEntity().getGraphicElement().translate(displacement, 0);
            buzzyBeetle.setSpeedX((int) -Math.signum(displacement) * 6);
        } else {
            m.getCollider().getEntity().die();
            m.getCollider().getEntity().subtractPoints(getEntity().subtractPoints());
        }
    }

    @Override
    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        if(collisionDirection == Direction.DOWN) {
            if (buzzyBeetle.getShell()) {
                m.getCollider().getEntity().addPoints(getEntity().getPoints());
            }
            buzzyBeetle.recieveDamage();
            int displacement = m.getCollider().displaceY(collision, 3);
            m.getCollider().getEntity().getGraphicElement().translate(0, displacement);
            m.getCollider().getEntity().addSpeed(0, 15);
        } else {
            if (!buzzyBeetle.getShell()) {
                m.getCollider().getEntity().die();
                m.getCollider().getEntity().subtractPoints(getEntity().subtractPoints());
            }
        }
    }
}
