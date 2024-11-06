package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.VisitorCollision;
import collisions.updateables.enemies.KoopaTroopaCollision;
import entities.updateables.enemies.KoopaTroopa;
import utils.Axis;

public class KoopaTroopaCollider extends ShellEnemyCollider {
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
    public KoopaTroopaCollision getCollision() {
        return new KoopaTroopaCollision(this);
    }

    @Override
    public void recieveCollision(VisitorCollision c, Axis a) {
        c.collide(this, a);
    }
}
