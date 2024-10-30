package colliders.updateables.enemies;

import java.awt.Point;
import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.updateables.UpdateableEntityCollider;
import colliders.updateables.mario.InvulnerableCollider;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.enemies.ShellEnemyCollision;
import collisions.updateables.mario.InvulnerableCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.mario.SuperMarioCollision;
import entities.updateables.enemies.Enemy;
import entities.updateables.mario.Mario;
import utils.Direction;

public abstract class EnemyCollider extends BaseCollider implements UpdateableEntityCollider {
    protected static int DISPLACEMENT_COEFFICIENT = 0;

    public abstract Enemy getEntity();

    public EnemyCollider(Rectangle b) {
        super(b);
    }

    public void handleHorizontalCollision(InvulnerableCollision m) {
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleHorizontalCollision(SuperMarioCollision m) {
        m.getCollider().getEntity().removeState();
    }

    public Direction calculateCollisionDirection(MarioCollision m) {
        Direction collisionDirection = Direction.UP;

        Rectangle marioBounds = m.getCollider().getBounds();
        Point marioPosition = new Point((int) marioBounds.getCenterX(), (int) marioBounds.getCenterY());
        int outcode = this.getBounds().outcode(marioPosition);

        if ((outcode & Rectangle.OUT_BOTTOM) == Rectangle.OUT_BOTTOM
                && m.getCollider().getEntity().getSpeedY() < 0) {
            collisionDirection = Direction.DOWN;
        }

        return collisionDirection;
    }

    public void handleVerticalCollision(MarioCollision m) {
        Mario mario = m.getCollider().getEntity();

        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
        } else {
            mario.die();
        }
    }

    public void handleVerticalCollision(InvulnerableCollision m) {
        Mario mario = m.getCollider().getEntity();

        if (calculateCollisionDirection(m) == Direction.DOWN) {
            getEntity().recieveDamage();
            mario.addSpeed(0, Mario.FIXED_BOUNCE_SPEED);
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
        if (!s.getCollider().getEntity().getShell() || s.getCollider().getEntity().getSpeedX() == 0) {
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
