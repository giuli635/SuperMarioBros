package colliders.solids;

import java.awt.Rectangle;

import colliders.BaseCollider;
import colliders.Collider;
import collisions.updateables.BouncerCollision;
import collisions.updateables.FireBallCollision;
import collisions.updateables.UpdateableEntityCollision;
import collisions.updateables.enemies.EnemyCollision;
import collisions.updateables.mario.MarioCollision;
import collisions.updateables.powerups.PowerUpCollision;
import entities.updateables.enemies.Enemy;
import entities.updateables.mario.Mario;

public abstract class SolidCollider extends BaseCollider {
    public SolidCollider(Rectangle b) {
        super(b);
    }

    public int displaceHorizontally(Collider c) {
        Rectangle collision = getBounds().intersection(c.getBounds());

        int displacement = c.displaceX(collision, 0);
        c.getEntity().getGraphicElement().translate(displacement, 0);
        return displacement;
    }

    public int displaceVertically(Collider c) {
        Rectangle collision = getBounds().intersection(c.getBounds());

        int displacement = c.displaceY(collision, 0);
        c.getEntity().getGraphicElement().translate(0, displacement);

        return displacement;
    }

    public void handleHorizontalCollision(UpdateableEntityCollision e) {
        displaceHorizontally(e.getCollider());
    }

    public void handleVerticalCollision(UpdateableEntityCollision e) {
        displaceVertically(e.getCollider());
    }

    public void handleHorizontalCollision(MarioCollision m) {
        displaceHorizontally(m.getCollider());
        m.getCollider().getEntity().setSpeedX(0);
    }

    public void handleVerticalCollision(MarioCollision m) {
        int displacement = displaceVertically(m.getCollider());
        Mario mario = m.getCollider().getEntity();
        if (displacement > 0) {
            mario.land();
        }
    }

    public void handleHorizontalCollision(EnemyCollision e) {
        displaceHorizontally(e.getCollider());
        e.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(EnemyCollision e) {
        int displacement = displaceVertically(e.getCollider());
        Enemy enemy = e.getCollider().getEntity();
        if (displacement > 0) {
            enemy.land();
        }
    }

    public void handleHorizontalCollision(PowerUpCollision p) {
        displaceHorizontally(p.getCollider());
        p.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(BouncerCollision s) {
        displaceVertically(s.getCollider());
        s.getCollider().getEntity().bounce();
    }

    public void handleHorizontalCollision(FireBallCollision p) {
        displaceVertically(p.getCollider());
        p.getCollider().getEntity().destroy();
    }
}
