package colliders.enemies;

import java.awt.Rectangle;

import colliders.Direction;
import collisions.MarioCollision;
import entities.enemies.ShellEnemy;
import entities.mario.Mario;

public abstract class ShellEnemyCollider extends EnemyCollider{
    public abstract ShellEnemy getEntity();

    public ShellEnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        if (!getEntity().getShell()) {
            mario.die();
            mario.subtractPoints(getEntity().subtractPoints());
        } else if(getEntity().getSpeedX() == 0) {
            int displacement = m.getCollider().displaceX(collision, 3);
            mario.getGraphicElement().translate(displacement, 0);
            getEntity().setSpeedX((int) -Math.signum(displacement) * 6);
        } else {
            mario.die();
            mario.subtractPoints(getEntity().subtractPoints());
        }
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();
        if(collisionDirection == Direction.DOWN) {
            if (getEntity().getShell()) {
                mario.addPoints(getEntity().getPoints());
            }
            getEntity().recieveDamage();
            int displacement = m.getCollider().displaceY(collision, 3);
            mario.getGraphicElement().translate(0, displacement);
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            if (!getEntity().getShell()) {
                mario.die();
                mario.subtractPoints(getEntity().subtractPoints());
            }
        }
    }
}
