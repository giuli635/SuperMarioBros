package colliders.enemies;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.Direction;
import colliders.UpdateableEntityCollider;
import collisions.EnemyCollision;
import collisions.MarioCollision;
import collisions.ShellEnemyCollision;
import entities.enemies.Enemy;
import entities.mario.Mario;

public abstract class EnemyCollider extends BaseCollider implements UpdateableEntityCollider {
    protected static int DISPLACEMENT_COEFFICIENT = 0;
    public abstract Enemy getEntity();

    public EnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
        m.getCollider().getEntity().subtractPoints(getEntity().getPoints());
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = Direction.verticalDirectionFromSign(
            (int) m.getCollider().getVelocity().getYComponent()
        );
        Mario mario = m.getCollider().getEntity();

        if(collisionDirection == Direction.DOWN) {
            m.getCollider().getEntity().setKillEnemySound();
            getEntity().recieveDamage();
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
            mario.addPoints(getEntity().getPoints());
        } else {
            mario.die();
            mario.subtractPoints(getEntity().subtractPoints());
        }
    }

    public void handleVerticalCollision(ShellEnemyCollision s) {
        if (!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
            Rectangle collision = getBounds().intersection(s.getCollider().getBounds());

            int displacement = s.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
            s.getCollider().getEntity().getGraphicElement().translate(0, displacement);
        } else {
            getEntity().recieveDamage();
        }
    }

    public void handleHorizontalCollision(ShellEnemyCollision s) {
        if(!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
            bounce(s.getCollider());
        } else {
            getEntity().recieveDamage();
        }
    }

    public void handleVerticalCollision(EnemyCollision e) {
        Rectangle collision = getBounds().intersection(e.getCollider().getBounds());
        Enemy enemy = e.getCollider().getEntity();

        int displacement = e.getCollider().displaceY(collision, DISPLACEMENT_COEFFICIENT);
        enemy.getGraphicElement().translate(0, displacement);
        displacement = e.getCollider().displaceX(collision, DISPLACEMENT_COEFFICIENT);
        enemy.getGraphicElement().translate(displacement, 0);
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
