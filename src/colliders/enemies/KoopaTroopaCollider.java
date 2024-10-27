package colliders.enemies;

import java.awt.Rectangle;

import colliders.Direction;
import collisions.Axis;
import collisions.Collision;
import collisions.KoopaTroopaCollision;
import collisions.MarioCollision;
import entities.enemies.KoopaTroopa;

public class KoopaTroopaCollider extends EnemyCollider {
    protected KoopaTroopa koopa;

    public KoopaTroopaCollider(KoopaTroopa k, Rectangle b) {
        super(b);
        koopa = k;
    }

    @Override
    public KoopaTroopa getEntity() {
        return koopa;
    }

    @Override
    public KoopaTroopaCollision getCollision() {
        return new KoopaTroopaCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        if (!koopa.getShell()) {
            m.getCollider().getEntity().die();
            m.getCollider().getEntity().subtractPoints(getEntity().subtractPoints());
        } else if(koopa.getSpeedX() == 0) {
            int displacement = m.getCollider().displaceX(collision, 3);
            m.getCollider().getEntity().getGraphicElement().translate(displacement, 0);
            koopa.setSpeedX((int) -Math.signum(displacement) * 6);
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
            koopa.recieveDamage();
            m.getCollider().getEntity().addPoints(getEntity().getPoints());
            int displacement = m.getCollider().displaceY(collision, 3);
            m.getCollider().getEntity().getGraphicElement().translate(0, displacement);
            m.getCollider().getEntity().addSpeed(0, 15);
        } else {
            if (!koopa.getShell()) {
                m.getCollider().getEntity().die();
                m.getCollider().getEntity().subtractPoints(getEntity().subtractPoints());
            }
        }
    }
}
