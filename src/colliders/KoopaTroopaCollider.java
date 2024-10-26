package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EnemyCollision;
import collisions.KoopaTroopaCollision;
import collisions.MarioCollision;
import entities.KoopaTroopa;

public class KoopaTroopaCollider extends BaseCollider implements EnemyCollider {
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
    public Collision getCollision() {
        return new KoopaTroopaCollision(this);
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    public void handleHorizontalCollision(KoopaTroopaCollision k){
    }

    public void handleVerticalCollision(KoopaTroopaCollision k){
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Rectangle collision = getBound().intersection(m.getCollider().getBound());
        if (!koopa.getShell()) {
            m.getCollider().getEntity().die();
        } else if(koopa.getSpeedX() == 0) {
            koopa.setVelocityX(4);
            int sign = (int) -Math.signum(velocity.getXComponent());
            m.getCollider().translate(sign * (int) (collision.getWidth()), 0);
            m.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        } else {
            m.getCollider().getEntity().die();
        }
    }

    public void handleVerticalCollision(MarioCollision m) {
        Direction collisionDirection = m.getCollider().getVelocity().getYComponent() > 0 ? Direction.UP : Direction.DOWN;
        Rectangle collision = getBound().intersection(m.getCollider().getBound());
        if(collisionDirection == Direction.DOWN) {
           if (!koopa.getShell()) {
                koopa.recieveDamage();
                m.getCollider().getEntity().addVelocity(0, 15);
           } else {
                koopa.recieveDamage();
                m.getCollider().getEntity().addVelocity(0, 15);
                int sign = (int) -Math.signum(velocity.getYComponent());
                m.getCollider().translate(0, sign * (int) collision.getHeight());
                m.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
           }
        } else {
            if (!koopa.getShell()) {
                m.getCollider().getEntity().die();
            }
        }
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
