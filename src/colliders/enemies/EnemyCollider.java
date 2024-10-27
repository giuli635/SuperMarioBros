package colliders.enemies;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.Direction;
import colliders.UpdateableEntityCollider;
import collisions.EnemyCollision;
import collisions.KoopaTroopaCollision;
import collisions.MarioCollision;
import entities.enemies.Enemy;

public abstract class EnemyCollider extends BaseCollider implements UpdateableEntityCollider {
    protected static int DISPLACEMENT_COEFFICIENT = 0;
    public abstract Enemy getEntity();

    public EnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = Direction.verticalDirectionFromSign(
            (int) m.getCollider().getVelocity().getYComponent()
        );

        if(collisionDirection == Direction.DOWN) {
            getEntity().recieveDamage();
            m.getCollider().getEntity().addSpeed(0, 8);
        } else {
            m.getCollider().getEntity().die();
        }
    }

    public void handleVerticalCollision(KoopaTroopaCollision k) {
        if (!k.getCollider().getEntity().getShell() || k.getCollider().getEntity().getSpeedX() == 0) {
            Rectangle collision = getBounds().intersection(k.getCollider().getBounds());

            int displacement = k.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
            k.getCollider().getEntity().getGraphicElement().translate(0, displacement);
        } else {
            getEntity().recieveDamage();
        }
    }

    public void handleHorizontalCollision(KoopaTroopaCollision k) {
        if(!k.getCollider().getEntity().getShell() || k.getCollider().getEntity().getSpeedX() == 0) {
            bounce(k.getCollider());
        } else {
            getEntity().recieveDamage();
        }
    }

    public void handleVerticalCollision(EnemyCollision e) {
        Rectangle collision = getBounds().intersection(e.getCollider().getBounds());

        int displacement = e.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
        e.getCollider().getEntity().getGraphicElement().translate(0, displacement);
        displacement = e.getCollider().displaceX(collision, DISPLACEMENT_COEFFICIENT);
        e.getCollider().getEntity().getGraphicElement().translate(displacement, 0);
    }

    public void handleHorizontalCollision(EnemyCollision e) {
        bounce(e.getCollider());
    }

    protected void bounce(EnemyCollider e) {
        Rectangle collision = getBounds().intersection(e.getBounds());

        int displacement = e.displaceX(collision, DISPLACEMENT_COEFFICIENT);
        e.getEntity().getGraphicElement().translate(displacement, 0);

        e.getEntity().switchDirection();
        getEntity().switchDirection();
    }
}
