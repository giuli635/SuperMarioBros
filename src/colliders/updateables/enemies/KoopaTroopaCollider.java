package colliders.updateables.enemies;

import java.awt.Rectangle;

import collisions.Collision;
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
    public void sendCollision(Collision c, Axis a) {
        c.collide(this, a);
    }
}
