package colliders;

import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EnemyCollision;
import collisions.MarioCollision;
import collisions.PipeCollision;
import collisions.PiranhaPlantCollision;
import entities.Mario;
import entities.Pipe;

public class PipeCollider extends BaseCollider {
    protected Pipe pipe;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public PipeCollider(Pipe e, Rectangle b) {
        super(b);
        pipe = e;
    }

    public Pipe getEntity() {
        return pipe;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new PipeCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        Vector2D velocity = m.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(m.getCollider().getBound());
        Mario mario = m.getCollider().getEntity();

        int sign = (int) -Math.signum(velocity.getYComponent());
        m.getCollider().translate(0, sign * (int) collision.getHeight());
        m.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
        if (sign == 1) {
            mario.land();
        }
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Vector2D velocity = m.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(m.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getXComponent());
        m.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        m.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
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

        int sign = (int) -Math.signum(velocity.getXComponent());
        e.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        e.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        e.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(PiranhaPlantCollision p) {
    }

    public void handleHorizontalCollision(PiranhaPlantCollision p) {
    }
}
