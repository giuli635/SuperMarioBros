package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EnemyCollision;
import collisions.GoombaCollision;
import collisions.KoopaTroopaCollision;
import collisions.MarioCollision;
import entities.Goomba;

public class GoombaCollider extends BaseCollider implements EnemyCollider {
    protected Goomba goomba;

    public GoombaCollider(Goomba g, Rectangle b) {
        super(b);
        goomba = g;
    }

    @Override
    public Goomba getEntity() {
        return goomba;
    }

    @Override
    public Collision getCollision() {
        return new GoombaCollision(this);
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
        if(collisionDirection == Direction.DOWN) {
            goomba.recieveDamage();
        } else {
            m.getCollider().getEntity().die();
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
            goomba.recieveDamage();
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
            goomba.recieveDamage();
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
}
