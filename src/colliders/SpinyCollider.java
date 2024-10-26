package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EnemyCollision;
import collisions.KoopaTroopaCollision;
import collisions.MarioCollision;
import collisions.SpinyCollision;
import entities.Spiny;

public class SpinyCollider extends BaseCollider implements EnemyCollider {
    protected Spiny spiny;

    public SpinyCollider(Spiny s, Rectangle b) {
        super(b);
        spiny = s;
    }

    @Override
    public Spiny getEntity() {
        return spiny;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new SpinyCollision(this);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(KoopaTroopaCollision k) {
        if (!k.getCollider().getEntity().getShell() || k.getCollider().getEntity().getSpeedX() == 0) {
            Vector2D velocity = k.getCollider().getVelocity();
            Rectangle collision = getBound().intersection(k.getCollider().getBound());
        
            int sign = (int) -Math.signum(velocity.getYComponent());
            k.getCollider().translate(0, sign * (int) collision.getHeight());
            k.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
        } else {
            spiny.recieveDamage();
        }
    }

    public void handleHorizontalCollision(KoopaTroopaCollision k) {
        if(!k.getCollider().getEntity().getShell() || k.getCollider().getEntity().getSpeedX() == 0) {
            Vector2D velocity = k.getCollider().getVelocity();
            Rectangle collision = getBound().intersection(k.getCollider().getBound());
            int otherDirection = (int) Math.signum(velocity.getXComponent());
            int myDirection = (int) Math.signum(this.velocity.getXComponent());

            int sign = (int) -Math.signum(velocity.getXComponent());
            k.getCollider().translate(sign * (int) (collision.getWidth()), 0);
            k.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
            if (otherDirection != myDirection) {
                k.getCollider().getEntity().switchDirection();
            }
        } else {
            spiny.recieveDamage();
        }
    }

    public void handleHorizontalCollision(SpinyCollision s) {
    }

    public void handleVerticalCollision(SpinyCollision s) {
    }

    public void handleVerticalCollision(EnemyCollision e) {
        Vector2D velocity = e.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(e.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getYComponent());
        e.getCollider().translate(0, sign * (int) collision.getHeight());
        e.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
    }

    public void handleHorizontalCollision(EnemyCollision e) {
        Vector2D velocity = e.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(e.getCollider().getBound());
        int otherDirection = (int) Math.signum(velocity.getXComponent());
        int myDirection = (int) Math.signum(this.velocity.getXComponent());

        int sign = (int) -Math.signum(velocity.getXComponent());
        e.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        e.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        if (otherDirection != myDirection) {
            e.getCollider().getEntity().switchDirection();
        }
    }
}
