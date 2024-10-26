package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EnemyCollision;
import collisions.KoopaTroopaCollision;
import collisions.LakituCollision;
import collisions.MarioCollision;
import entities.Lakitu;

public class LakituCollider extends BaseCollider implements EnemyCollider {
    protected Lakitu lakitu;

    public LakituCollider(Lakitu l, Rectangle b) {
        super(b);
        lakitu = l;
    }

    @Override
    public Lakitu getEntity() {
        return lakitu;
    }

    @Override
    public Collision getCollision() {
        return new LakituCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    public void handleHorizontalCollision(MarioCollision m) {
        m.getCollider().getEntity().die();
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        if(collisionDirection == Direction.DOWN && m.getCollider().getEntity().getJumping()) { //TODO: make this more robust
            lakitu.recieveDamage();
            m.getCollider().getEntity().addVelocity(0, 8);
        } else {
            m.getCollider().getEntity().die();
        }
    }

    public void handleVerticalCollision(EnemyCollision e) {
        Vector2D velocity = e.getCollider().getVelocity();
        Rectangle collision = getBounds().intersection(e.getCollider().getBounds());

        int sign = (int) -Math.signum(velocity.getYComponent());
        e.getCollider().translate(0, sign * (int) collision.getHeight());
        e.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
    }

    public void handleHorizontalCollision(EnemyCollision e) {
        Vector2D velocity = e.getCollider().getVelocity();
        Rectangle collision = getBounds().intersection(e.getCollider().getBounds());
        int otherDirection = (int) Math.signum(velocity.getXComponent());
        int myDirection = (int) Math.signum(this.velocity.getXComponent());

        int sign = (int) -Math.signum(velocity.getXComponent());
        e.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        e.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        if (otherDirection != myDirection) {
            e.getCollider().getEntity().switchDirection();
        }
    }

    public void handleVerticalCollision(KoopaTroopaCollision k) {
        if (!k.getCollider().getEntity().getShell() || k.getCollider().getEntity().getSpeedX() == 0) {
            Vector2D velocity = k.getCollider().getVelocity();
            Rectangle collision = getBounds().intersection(k.getCollider().getBounds());
        
            int sign = (int) -Math.signum(velocity.getYComponent());
            k.getCollider().translate(0, sign * (int) collision.getHeight());
            k.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
        } else {
            lakitu.recieveDamage();
        }
    }

    public void handleHorizontalCollision(KoopaTroopaCollision k) {
        if(!k.getCollider().getEntity().getShell() || k.getCollider().getEntity().getSpeedX() == 0) {
            Vector2D velocity = k.getCollider().getVelocity();
            Rectangle collision = getBounds().intersection(k.getCollider().getBounds());
            int otherDirection = (int) Math.signum(velocity.getXComponent());
            int myDirection = (int) Math.signum(this.velocity.getXComponent());

            int sign = (int) -Math.signum(velocity.getXComponent());
            k.getCollider().translate(sign * (int) (collision.getWidth()), 0);
            k.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
            if (otherDirection != myDirection) {
                k.getCollider().getEntity().switchDirection();
            }
        } else {
            lakitu.recieveDamage();
        }
    }
}
