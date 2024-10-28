package colliders;

import java.awt.Rectangle;

import collisions.EnemyCollision;
import collisions.MarioCollision;
import collisions.PowerUpCollision;
import collisions.StarCollision;
import collisions.UpdateableEntityCollision;
import entities.mario.Mario;

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
        displaceVertically(e.getCollider());
    }

    public void handleHorizontalCollision(PowerUpCollision p) {
        displaceHorizontally(p.getCollider());
        p.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(PowerUpCollision p) {
        displaceVertically(p.getCollider());
    }

    public void handleVerticalCollision(StarCollision s) {
        displaceVertically(s.getCollider());
        s.getCollider().getEntity().startBounce();
    }
}
