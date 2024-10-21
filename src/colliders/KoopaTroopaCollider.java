package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.KoopaTroopaCollision;
import entities.KoopaTroopa;

public class KoopaTroopaCollider extends BaseCollider implements UpdateableEntityCollider {
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
}
