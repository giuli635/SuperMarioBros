package colliders;

import java.awt.Rectangle;

import collisions.Axis;
import collisions.Collision;
import collisions.KoopaTroopaCollision;
import entities.Enemy;
import entities.Entity;
import entities.KoopaTroopa;

public class KoopaTroopaCollider extends BaseCollider {
    protected Enemy koopa;

    public KoopaTroopaCollider(Enemy k, Rectangle b) {
        super(b);
        koopa = k;
    }

    @Override
    public Entity getEntity() {
        return koopa;
    }

    public KoopaTroopa getKoopaTroopa() {
        return  (KoopaTroopa)koopa;
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
