package colliders;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.EnemyCollision;
import collisions.MarioCollision;
import collisions.PiranhaPlantCollision;
import collisions.QuestionBlockCollision;
import entities.mario.Mario;
import entities.QuestionBlock;

public class QuestionBlockCollider extends SolidCollider {
    protected QuestionBlock questionBlock;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public QuestionBlockCollider(QuestionBlock e, Rectangle b) {
        super(b);
        questionBlock = e;
    }

    public QuestionBlock getEntity() {
        return questionBlock;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public QuestionBlockCollision getCollision() {
        return new QuestionBlockCollision(this);
    }

    public void handleVerticalCollision(MarioCollision m) {
        Vector2D velocity = m.getCollider().getVelocity();
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());
        Mario mario = m.getCollider().getEntity();

        int sign = (int) -Math.signum(velocity.getYComponent());
        if (sign == 1) {
            m.getCollider().translate(0, sign * (int) collision.getHeight());
            mario.getGraphicElement().translate(0, sign * (int) collision.getHeight());
            mario.land();
        } else {
            translate(0, -sign * (int) collision.getHeight());
            getEntity().getGraphicElement().translate(0, -sign * (int) collision.getHeight());
            questionBlock.interaction();
        }
    }

    public void handleHorizontalCollision(MarioCollision m) {
        Vector2D velocity = m.getCollider().getVelocity();
        Rectangle collision = getBounds().intersection(m.getCollider().getBounds());

        int sign = (int) -Math.signum(velocity.getXComponent());
        m.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        m.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
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
