package colliders;
import java.awt.Point;
import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.GoombaCollision;
import collisions.KoopaTroopaCollision;
import collisions.BlockCollision;
import collisions.MarioCollision;
import collisions.SpinyCollision;
import collisions.SuperMushroomCollision;
import entities.Block;
import entities.Mario;

public class BlockCollider extends BaseCollider {
    protected Block block;
    protected Rectangle bounds;
    protected Point previousPosition;
    protected boolean activated;

    public BlockCollider(Block e, Rectangle b) {
        super(b);
        block = e;
    }

    public Block getEntity() {
        return block;
    }

    @Override
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }

    @Override
    public Collision getCollision() {
        return new BlockCollision(this);
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

    public void handleVerticalCollision(GoombaCollision g) {
        Vector2D velocity = g.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(g.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getYComponent());
        g.getCollider().translate(0, sign * (int) collision.getHeight());
        g.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
    }

    public void handleHorizontalCollision(GoombaCollision g) {
        Vector2D velocity = g.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(g.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getXComponent());
        g.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        g.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        g.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(KoopaTroopaCollision k) {
        Vector2D velocity = k.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(k.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getYComponent());
        k.getCollider().translate(0, sign * (int) collision.getHeight());
        k.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
    }

    public void handleHorizontalCollision(KoopaTroopaCollision k) {
        Vector2D velocity = k.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(k.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getXComponent());
        k.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        k.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        k.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(SpinyCollision s) {
        Vector2D velocity = s.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(s.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getYComponent());
        s.getCollider().translate(0, sign * (int) collision.getHeight());
        s.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
    }

    public void handleHorizontalCollision(SpinyCollision s) {
        Vector2D velocity = s.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(s.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getXComponent());
        s.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        s.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        s.getCollider().getEntity().switchDirection();
    }

    public void handleVerticalCollision(SuperMushroomCollision s) {
        Vector2D velocity = s.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(s.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getYComponent());
        s.getCollider().translate(0, sign * (int) collision.getHeight());
        s.getCollider().getEntity().getGraphicElement().translate(0, sign * (int) collision.getHeight());
    }

    public void handleHorizontalCollision(SuperMushroomCollision s) {
        Vector2D velocity = s.getCollider().getVelocity();
        Rectangle collision = getBound().intersection(s.getCollider().getBound());

        int sign = (int) -Math.signum(velocity.getXComponent());
        s.getCollider().translate(sign * (int) (collision.getWidth()), 0);
        s.getCollider().getEntity().getGraphicElement().translate(sign * (int) (collision.getWidth()), 0);
        s.getCollider().getSuperMushroom().switchDirection();
    }
}
